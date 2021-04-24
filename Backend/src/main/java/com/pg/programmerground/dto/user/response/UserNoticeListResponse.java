package com.pg.programmerground.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserNoticeListResponse {

    @JsonProperty(value = "user_notice")
    private final List<UserNoticeResponse> userNoticeResponseList;

    public UserNoticeListResponse(List<UserNoticeResponse> userNoticeResponseList) {
        this.userNoticeResponseList = userNoticeResponseList;
    }
}
