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

    //JWT 로그인 코드
    @Column(name = "CODE")
    private String code;

    @OneToOne
    @JoinColumn(name = "OAUTH_ID")
    private Oauth2AuthorizedClient oauth2AuthorizedClient;
}
