package com.pg.pgp.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserApplyNoticeListResponse {

    @JsonProperty(value = "user_notice")
    private final List<UserApplyNoticeResponse> userApplyNoticeResponses;

    public UserApplyNoticeListResponse(List<UserApplyNoticeResponse> userApplyNoticeResponseList) {
        this.userApplyNoticeResponses = userApplyNoticeResponseList;
    }
}
