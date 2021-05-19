package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.user.response.UserApplyNoticeListResponse;
import com.pg.programmerground.dto.user.response.UserLeaderNoticeListResponse;
import com.pg.programmerground.dto.user.response.UserResponse;
import com.pg.programmerground.service.NoticeService;
import com.pg.programmerground.service.OAuthUserService;
import com.pg.programmerground.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> userInfo() {
        return ResponseEntity.ok().body(new ApiResponse<>(oAuthUserService.getUserInfo(UserAuthenticationService.getUserId())));
    }

    /**
     * User가 Leader인 Playground의 참여 신청 알림 리스트
     */
    @GetMapping("/notices/leader")
    public ResponseEntity<ApiResponse<UserLeaderNoticeListResponse>> getUserNotice() {
        return ResponseEntity.ok().body(new ApiResponse<>(noticeService.getUserNoticeList(UserAuthenticationService.getUserId())));
    }

    /**
     * User가 신청한 Playground중 대기 상태인 Playground 리스트
     */
    //취소 기능도 넣어야겠네
    //취소일 경우 status cancel??
    @GetMapping("/notices/waitings")
    public ResponseEntity<ApiResponse<UserApplyNoticeListResponse>> getUserWaitingNotice() {
        return ResponseEntity.ok().body(new ApiResponse<>(noticeService.getUserStatusNoticeList(UserAuthenticationService.getUserId(), "wait")));
    }

    /**
     * User가 신청한 Playground중 수락, 거절 상태인 Playground 리스트
     */
    @GetMapping("/notices/results")
    public ResponseEntity<ApiResponse<UserApplyNoticeListResponse>> getUserResultNotice() {
        return ResponseEntity.ok().body(new ApiResponse<>(noticeService.getUserStatusNoticeList(UserAuthenticationService.getUserId(), "result")));
    }


}
