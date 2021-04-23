package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.user.response.UserNoticeListResponse;
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
     * 사용자 정보 가져오기
     * "commitCnt": 530,
     * "pullRequestCnt": 0,
     * "mostLanguage": "Java,PHP,C#",
     * "repositoryCnt": 33,
     * "githubPage": "https://github.com/CJW23",
     * "profileImg": "https://avatars.githubusercontent.com/u/32676275?v=4",
     * "role": "ROLE_USER,SCOPE_read:user",
     * "oauthName": "CJW23"
     */
    @GetMapping("")
    public ResponseEntity<ApiResponse<UserResponse>> userInfo() {
        return ResponseEntity.ok().body(new ApiResponse<>(oAuthUserService.getUserInfo()));
    }

    /**
     * User가 Leader인 Playground의 참여 신청 알림 리스트
     */
    @GetMapping("/notice")
    public ResponseEntity<ApiResponse<UserNoticeListResponse>> getUserNotice() {
        return ResponseEntity.ok().body(new ApiResponse<>(noticeService.getUserNoticeList(UserAuthenticationService.getUserId())));
    }
}
