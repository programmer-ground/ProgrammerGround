package com.pg.pgp.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserLeaderNoticeListResponse {

    @JsonProperty(value = "user_notice")
    private final List<UserLeaderNoticeResponse> userLeaderNoticeResponseList;

    public UserLeaderNoticeListResponse(List<UserLeaderNoticeResponse> userLeaderNoticeResponseList) {
        this.userLeaderNoticeResponseList = userLeaderNoticeResponseList;
    }
}
