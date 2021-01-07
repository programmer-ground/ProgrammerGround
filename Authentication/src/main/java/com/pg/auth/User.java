package com.pg.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID")
    private String id;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "OAUTH_ID")
    private String oauthId;

    //oauth제공 : github
    @Column(name = "OAUTH_REGI")
    private String oauthRegistration;

    @Column(name = "USER_NAME")
    private String userName;

}
