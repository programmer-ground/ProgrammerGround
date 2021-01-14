package com.pg.auth.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
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

    @OneToOne
    @JoinColumn(name = "OAUTH_ID")
    private Oauth2AuthorizedClient oauth2AuthorizedClient;
    ///repo수나 commit수는 저장할 필요가 있나 실시간 업데이트면
}
