package com.pg.pgp.dto.playground.response;

import com.pg.pgp.domain.Playground;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PlaygroundResponse {
    private final String title;
    private final String description;
    private final int maxUserNum;
    private final int currentUserNum;
    private final List<OAuthUserResponse> userInfoList;

    @Builder
    public PlaygroundResponse(String title, String description, int maxUserNum, int currentUserNum, List<OAuthUserResponse> userInfoList) {
        this.title = title;
        this.description = description;
        this.maxUserNum = maxUserNum;
        this.currentUserNum = currentUserNum;
        this.userInfoList = userInfoList;
    }

    public static PlaygroundResponse of(Playground playground) {
        return PlaygroundResponse.builder()
                .title(playground.getTitle())
                .description(playground.getDescription())
                .maxUserNum(playground.getMaxMemberCount())
                .currentUserNum(playground.getCurrentMemberCount())
                .userInfoList(OAuthUserResponse.ofList(playground))
                .build();
    }
}
