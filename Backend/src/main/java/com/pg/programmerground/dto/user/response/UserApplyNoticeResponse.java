package com.pg.programmerground.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.programmerground.domain.PlaygroundApply;
import com.pg.programmerground.domain.enumerated.ApplyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class UserApplyNoticeResponse {
    @JsonProperty(value = "playground_title")
    private final String playgroundTitle;
    @JsonProperty(value = "playground_apply_id")
    private final Long playgroundApplyId;
    private final String position;
    private final ApplyStatus status;
    private final LocalDateTime date;

    public static List<UserApplyNoticeResponse> ofList(List<PlaygroundApply> playgroundApplyList) {
        return playgroundApplyList.stream()
                .map(playgroundApply -> UserApplyNoticeResponse.builder()
                        .playgroundTitle(playgroundApply.getPlayground().getTitle())
                        .playgroundApplyId(playgroundApply.getId())
                        .status(playgroundApply.getApplyStatus())
                        .position(playgroundApply.getPlaygroundPosition().getPosition().name())
                        .date(playgroundApply.getCreatedAt()).build())
                .collect(Collectors.toList());
    }
}
