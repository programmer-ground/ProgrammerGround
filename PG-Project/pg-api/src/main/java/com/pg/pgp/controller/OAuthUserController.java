package com.pg.pgp.controller;

import com.pg.pgp.constant.PgConstant;
import com.pg.pgp.controller.response.ApiResponse;
import com.pg.pgp.dto.user.api_req.ReviseUserApi;
import com.pg.pgp.dto.user.response.UserApplyNoticeListResponse;
import com.pg.pgp.dto.user.response.UserLeaderNoticeListResponse;
import com.pg.pgp.dto.user.response.UserResponse;
import com.pg.pgp.service.NoticeService;
import com.pg.pgp.service.OAuthUserService;
import com.pg.pgp.service.UserAuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class OAuthUserController {
    private final OAuthUserService oAuthUserService;
    private final NoticeService noticeService;

    /**
     "user_id": 1,
     "oauth_id": 32676275,
     "oauth_name": "CJW23",
     "commit_cnt": 601,
     "puul_request_cnt": 0,
     "most_language": "Java,PHP,C#",
     "repository_cnt": 36,
     "github_page": "https://github.com/CJW23",
     "profile_img": "https://avatars.githubusercontent.com/u/32676275?v=4",
     "user_playgrounds": [
     {
         "playgroundId": 1,
         "title": "test",
         "maxMemberCount": 10,
         "currentMemberCount": 1
     }]
     */
    @ApiOperation(value = "유저 정보", notes = "JWT 토큰 인증된 유저의 정보")
    @GetMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> userInfo() {
        return ResponseEntity.ok().body(new ApiResponse<>(oAuthUserService.getUserInfo(UserAuthenticationService.getUserId())));
    }

    @ApiOperation(value = "유저 Leader 알림 리스트", notes = "유저가 Leader인 Playground의 신청 알림 리스트")
    @GetMapping("/notices/leader")
    public ResponseEntity<ApiResponse<UserLeaderNoticeListResponse>> getUserNotice() {
        return ResponseEntity.ok().body(new ApiResponse<>(noticeService.getUserNoticeList(UserAuthenticationService.getUserId())));
    }

    @ApiOperation(value = "유저 정보 수정", notes = "유저 정보를 수정")
    @PatchMapping("")
    public ResponseEntity<ApiResponse<Boolean>> updateUserInfo(@RequestBody @Validated ReviseUserApi userApi) {
        return ResponseEntity.ok().body(new ApiResponse<>(oAuthUserService.updateUserInfo(UserAuthenticationService.getUserId(), userApi)));
    }

    @ApiOperation(value = "유저 신청 Playground 대기 리스트",  notes = "유저가 신청한 Playground 리스트 중 대기중인 Playground")
    @GetMapping("/notices/waitings")
    public ResponseEntity<ApiResponse<UserApplyNoticeListResponse>> getUserWaitingNotice() {
        return ResponseEntity.ok().body(new ApiResponse<>(noticeService.getUserStatusNoticeList(UserAuthenticationService.getUserId(), PgConstant.NOTICE_WAIT)));
    }

    @ApiOperation(value = "유저 신청 Playground 결과 리스트", notes = "유저가 신청한 Playground중 결과(수락, 거절)가 나온 리스트")
    @GetMapping("/notices/results")
    public ResponseEntity<ApiResponse<UserApplyNoticeListResponse>> getUserResultNotice() {
        return ResponseEntity.ok().body(new ApiResponse<>(noticeService.getUserStatusNoticeList(UserAuthenticationService.getUserId(), PgConstant.NOTICE_RESULT)));
    }
}
