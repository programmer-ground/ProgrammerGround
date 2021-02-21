package com.pg.programmerground.dto.playground;

import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.dto.OAuthUserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PlaygroundInfoDto {
    private final String title;
    private final String description;
    private final int maxUserNum;
    private final int currentUserNum;
    private final List<OAuthUserInfo> oAuthUserInfos;

    @Builder
    public PlaygroundInfoDto(String title, String description, int maxUserNum, int currentUserNum, List<OAuthUserInfo> oAuthUserInfos) {
        this.title = title;
        this.description = description;
        this.maxUserNum = maxUserNum;
        this.currentUserNum = currentUserNum;
        this.oAuthUserInfos = oAuthUserInfos;
    }

    public static PlaygroundInfoDto of(Playground playground) {
        return PlaygroundInfoDto.builder()
                .title(playground.getTitle())
                .description(playground.getDescription())
                .maxUserNum(playground.getMaxMemberCount())
                .currentUserNum(playground.getCurrentMemberCount())
                .oAuthUserInfos(OAuthUserInfo.ofList(playground))
                .build();
    }
}
