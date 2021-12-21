package com.pg.pgp.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.pgp.domain.PlaygroundApply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User가 참여중인 Playground
 */
@Getter
@Builder
@AllArgsConstructor
public class UserPlaygroundResponse {
    @JsonProperty(value = "playground_id")
    private final Long playgroundId;
    private final String title;
    @JsonProperty(value = "max_member_count")
    private final int maxMemberCount;
    @JsonProperty(value = "current_member_count")
    private final int currentMemberCount;
    //추후 repository URL 추가

    public static List<UserPlaygroundResponse> ofList(List<PlaygroundApply> playgroundApplies) {
        return playgroundApplies.stream()
                .filter(playgroundApply -> playgroundApply.getPlayground().isRemovePlayground())
                .map(playgroundApply -> UserPlaygroundResponse.builder()
                        .playgroundId(playgroundApply.getPlayground().getId())
                        .title(playgroundApply.getPlayground().getTitle())
                        .maxMemberCount(playgroundApply.getPlayground().getMaxMemberCount())
                        .currentMemberCount(playgroundApply.getPlayground().getCurrentMemberCount()).build())
                .collect(Collectors.toList());
    }
}
