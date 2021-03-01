package com.pg.programmerground.domain.github;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.OAuthUser;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Entity(name = "oauth2_authorized_client")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Oauth2AuthorizedClient extends BaseTimeEntity {

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

    @OneToOne(mappedBy = "oauth2AuthorizedClient")
    private OAuthUser user;

    @Builder
    public Oauth2AuthorizedClient(Long id, String clientRegistrationId,
        String accessTokenType, String accessTokenValue, Date accessTokenIssuedAt,
        Date accessTokenExpiresAt, String accessTokenScopes,
        OAuthUser user) {
        this.id = id;
        this.clientRegistrationId = clientRegistrationId;
        this.accessTokenType = accessTokenType;
        this.accessTokenValue = accessTokenValue;
        this.accessTokenIssuedAt = accessTokenIssuedAt;
        this.accessTokenExpiresAt = accessTokenExpiresAt;
        this.accessTokenScopes = accessTokenScopes;
        this.user = user;
    }
}
