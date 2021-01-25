package com.pg.chat;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@EventListener(ApplicationStartedEvent.class)
	public void applicationStartedEvent(ApplicationStartedEvent event) {
		try {
			String hostIp = InetAddress.getLocalHost().getHostAddress(); // my be dns lookup is occurred
			Environment environment = event.getApplicationContext().getBean(Environment.class);
			int port = environment.getProperty("server.port", Integer.class, 8080);
			String profile = environment.getProperty("spring.profiles.active", String.class, "default");
			log.info("\n"
				+ "-------------------------------------------------------------\n"
				+ " + Server is Started.\n"
				+ " + IP: {}\n"
				+ " + PORT: {}\n"
				+ " + SERVER_MODE: {}\n"
				+ "-------------------------------------------------------------\n"
				+ "\n", hostIp, port, profile.toUpperCase());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
