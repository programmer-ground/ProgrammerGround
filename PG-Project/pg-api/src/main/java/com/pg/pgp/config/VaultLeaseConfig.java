package com.pg.pgp.config;

import com.zaxxer.hikari.HikariConfigMXBean;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.vault.core.lease.SecretLeaseContainer;
import org.springframework.vault.core.lease.domain.RequestedSecret;
import org.springframework.vault.core.lease.event.SecretLeaseCreatedEvent;
import org.springframework.vault.core.lease.event.SecretLeaseExpiredEvent;

import javax.annotation.PostConstruct;

public class VaultLeaseConfig {
    private static final Logger LOGGER = LogManager.getLogger(VaultLeaseConfig.class);

    @Value("${spring.cloud.vault.database.role}")
    private String databaseRole;

    private final ApplicationContext applicationContext;
    private final SecretLeaseContainer leaseContainer;

    public VaultLeaseConfig(ApplicationContext applicationContext, SecretLeaseContainer leaseContainer) {
        this.applicationContext = applicationContext;
        this.leaseContainer = leaseContainer;
    }

    @PostConstruct
    private void postConstruct() {
        final String vaultCredsPath = String.format("database/creds/%s", databaseRole);

        leaseContainer.addLeaseListener(event -> {
            LOGGER.info("==> Received event: {}", event);

            if (vaultCredsPath.equals(event.getSource().getPath())) {
                if (event instanceof SecretLeaseExpiredEvent && event.getSource().getMode() == RequestedSecret.Mode.RENEW) {
                    LOGGER.info("==> Replace RENEW lease by a ROTATE one.");
                    leaseContainer.requestRotatingSecret(vaultCredsPath);
                } else if (event instanceof SecretLeaseCreatedEvent && event.getSource().getMode() == RequestedSecret.Mode.ROTATE) {
                    SecretLeaseCreatedEvent secretLeaseCreatedEvent = (SecretLeaseCreatedEvent) event;
                    String username = (String) secretLeaseCreatedEvent.getSecrets().get("username");
                    String password = (String) secretLeaseCreatedEvent.getSecrets().get("password");

                    LOGGER.info("==> Update System properties username & password");
                    System.setProperty("spring.datasource.username", username);
                    System.setProperty("spring.datasource.password", password);

                    LOGGER.info("==> spring.datasource.username: {}", username);

                    updateDataSource(username, password);
                }
            }
        });
    }

    private void updateDataSource(String username, String password) {
        HikariDataSource hikariDataSource = (HikariDataSource) applicationContext.getBean("dataSource");

        LOGGER.info("==> Soft evict database connections");
        HikariPoolMXBean hikariPoolMXBean = hikariDataSource.getHikariPoolMXBean();
        if (hikariPoolMXBean != null) {
            hikariPoolMXBean.softEvictConnections();
        }

        LOGGER.info("==> Update database credentials");
        HikariConfigMXBean hikariConfigMXBean = hikariDataSource.getHikariConfigMXBean();
        hikariConfigMXBean.setUsername(username);
        hikariConfigMXBean.setPassword(password);
    }
}
