package com.pg.pgp.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.pgp.domain.PlaygroundApply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Builder
public class UserLeaderNoticeResponse {
    @JsonProperty(value = "playground_apply_id")
    private final Long playgroundApplyId;
    @JsonProperty(value = "playground_title")
    private final String playgroundTitle;
    private final String position;
    @JsonProperty(value = "user_name")
    private final String userName;
    private final LocalDateTime date;


    public static List<UserLeaderNoticeResponse> ofList(List<PlaygroundApply> playgroundApplyList) {
        return playgroundApplyList.stream()
                .map(playgroundApply -> UserLeaderNoticeResponse.builder()
                        .playgroundApplyId(playgroundApply.getId())
                        .playgroundTitle(playgroundApply.getPlayground().getTitle())
                        .userName(playgroundApply.getUser().getUserName())
                        .position(playgroundApply.getPlaygroundPosition().getPosition().name())
                        .date(playgroundApply.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }
}
