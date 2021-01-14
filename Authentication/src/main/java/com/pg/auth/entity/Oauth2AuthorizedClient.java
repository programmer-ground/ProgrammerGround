package com.pg.auth.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "oauth2_authorized_client")
@Getter
public class Oauth2AuthorizedClient {
    @Id
    @Column(name = "principal_name")
    private Long id;

    @Column(name = "client_registration_id")
    private String clientRegistrationId;

    @Column(name = "access_token_type")
    private String accessTokenType;

    @Column(name = "access_token_value")
    private String accessTokenValue;

    @Column(name = "access_token_issued_at")
    private Date accessTokenIssuedAt;

    @Column(name = "access_token_expires_at")
    private Date accessTokenExpiresAt;

    @Column(name = "access_token_scopes", columnDefinition = "VARCHAR(100) DEFAULT NULL")
    private String accessTokenScopes;

    @Column(name = "refresh_token_value", columnDefinition = "VARCHAR(100) DEFAULT NULL")
    private String refreshTokenValue;

    @Column(name = "refresh_token_issued_at", columnDefinition = "TIMESTAMP DEFAULT NULL")
    private Date refreshTokenIssuedAt;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @OneToOne(mappedBy = "oauth2AuthorizedClient")
    private User user;
}
