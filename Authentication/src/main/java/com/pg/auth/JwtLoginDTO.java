package com.pg.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtLoginDTO {
    private String code;
    private Long oauthId;
}
