package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import com.pg.programmerground.domain.github.UserGithubInfo;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "OAUTH_USER")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthUser extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    //사용자 이름
    @Column(name = "USER_NAME")
    private String userName;

    //Github 닉네임
    @Column(name = "OAUTH_NAME")
    private String OAuthName;

    @Column(name = "ROLE")
    private String Role;

    //JWT 로그인 인증 코드
    @Column(name = "CODE")
    private String code;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OAUTH_ID")
    private Oauth2AuthorizedClient oauth2AuthorizedClient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_GITHUB_INFO_ID")
    private UserGithubInfo userGithubInfo;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_EXTRA_INFO_ID")
    private UserExtraInfo userExtraInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PlaygroundApply> applyPlaygrounds = new ArrayList<>();

    @OneToMany(mappedBy = "leader", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Playground> leaderPlaygrounds = new ArrayList<>();

    @Builder
    public OAuthUser(String userName, String OAuthName, String Role, String code, Oauth2AuthorizedClient oauth2AuthorizedClient, UserGithubInfo userGithubInfo) {
        Assert.notNull(userName, "userName must not be null");
        Assert.notNull(OAuthName, "OAuthName must not be null");
        Assert.notNull(Role, "Role must not be null");
        Assert.notNull(oauth2AuthorizedClient, "AuthorizedClient must not be null");

        this.userGithubInfo = userGithubInfo;
        this.userName = userName;
        this.OAuthName = OAuthName;
        this.Role = Role;
        this.code = code;
        this.oauth2AuthorizedClient = oauth2AuthorizedClient;
    }
}
