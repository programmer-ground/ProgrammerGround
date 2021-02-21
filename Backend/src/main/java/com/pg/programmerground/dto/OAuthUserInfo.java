package com.pg.programmerground.dto;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.Playground;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OAuthUserInfo {
    private final Long userId;
    private final String oAuthName;
    private final String userName;
    private final String profileImg;

    @Builder
    public OAuthUserInfo(Long userId, String oAuthName, String userName, String profileImg) {
        this.userId = userId;
        this.oAuthName = oAuthName;
        this.userName = userName;
        this.profileImg = profileImg;
    }

    public static List<OAuthUserInfo> ofList(Playground playground) {
        return playground.getUserPlaygrounds().stream().map(oAuthUserPlayground -> {
            OAuthUser user = oAuthUserPlayground.getUser();
            return OAuthUserInfo.builder()
                    .oAuthName(user.getOAuthName())
                    .userId(user.getId())
                    .userName(user.getUserName())
                    .profileImg(user.getUserGithubInfo().getProfileImg())
                    .build();
        }).collect(Collectors.toList());
    }
}
