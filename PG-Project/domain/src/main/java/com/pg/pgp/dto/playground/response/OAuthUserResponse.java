package com.pg.pgp.dto.playground.response;

import com.pg.pgp.domain.OAuthUser;
import com.pg.pgp.domain.Playground;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OAuthUserResponse {
    private final Long userId;
    private final String oAuthName;
    private final String userName;
    private final String profileImg;

    @Builder
    public OAuthUserResponse(Long userId, String oAuthName, String userName, String profileImg) {
        this.userId = userId;
        this.oAuthName = oAuthName;
        this.userName = userName;
        this.profileImg = profileImg;
    }

    public static List<OAuthUserResponse> ofList(Playground playground) {
        return playground.getApplyPlaygrounds().stream().map(oAuthUserPlayground -> {
            OAuthUser user = oAuthUserPlayground.getUser();
            return OAuthUserResponse.builder()
                    .oAuthName(user.getOAuthName())
                    .userId(user.getId())
                    .userName(user.getUserName())
                    .profileImg(user.getUserGithubInfo().getProfileImg())
                    .build();
        }).collect(Collectors.toList());
    }
}
