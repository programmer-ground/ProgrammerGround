package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.playground.api_req.ApplyPlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.RevisePlaygroundApi;
import com.pg.programmerground.dto.playground.response.PlaygroundCardListResponse;
import com.pg.programmerground.dto.playground.response.PlaygroundResponse;
import com.pg.programmerground.dto.playground.response.PlaygroundResultResponse;
import com.pg.programmerground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/playground")
public class PlaygroundController {
    private final PlaygroundService playgroundService;
    /**
     * playground 목록 반환
     * 무한 스크롤 적용위한 방법? 찾기
     */
    @GetMapping("")
    public ResponseEntity<ApiResponse<PlaygroundCardListResponse>> playgroundList() {
        return ResponseEntity.ok().body(new ApiResponse<>(new PlaygroundCardListResponse(playgroundService.getPlaygroundCardList())));
    }

    /**
     * playground 생성
     */
    @PostMapping("")
    public ResponseEntity<ApiResponse<Long>> createPlayground(@Valid @RequestBody PlaygroundApi info) throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.createPlayground(info)));
    }

    /**
     * playground 참여 신청
     */
    @PostMapping("/{playgroundId}/apply")
    public ResponseEntity<ApiResponse<PlaygroundResultResponse>> applyPlayground(@PathVariable Long playgroundId, @RequestBody ApplyPlaygroundApi applyPlayground) throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(new PlaygroundResultResponse(playgroundService.applyPlayground(playgroundId, applyPlayground))));
    }

    /**
     * playground 신청 수락
     */
    @PutMapping("/applicants/{playgroundApplyId}/accept")
    public ResponseEntity<ApiResponse<PlaygroundResultResponse>> acceptPlaygroundApply(@PathVariable Long playgroundApplyId) {
        return ResponseEntity.ok().body(new ApiResponse<>(new PlaygroundResultResponse(playgroundService.acceptPlayground(playgroundApplyId))));
    }

    /**
     * playground 신청 거절
     */
    @PutMapping("/applicants/{playgroundApplyId}/reject")
    public ResponseEntity<ApiResponse<Boolean>> rejectPlaygroundApply(@PathVariable Long playgroundApplyId) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.rejectPlayground(playgroundApplyId)));
    }

    /**
     * playground 상세 정보
     */
    @GetMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<PlaygroundResponse>> getPlayground(@PathVariable Long playgroundId) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.getPlaygroundDetailInfo(playgroundId)));
    }

    /**
     * playground 수정
     */
    @PutMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<Integer>> revisePlayground(@Valid RevisePlaygroundApi info, @PathVariable Long playgroundId) {
        return ResponseEntity.accepted().body(new ApiResponse<>(null));
    }

    /**
     * playground 삭제
     */
    @DeleteMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<Integer>> deletePlayground(@PathVariable Long playgroundId) {
        return ResponseEntity.ok().body(new ApiResponse<>(null));
    }

    /**
     * Playground 레포 생성 요청
     * TODO : 응답 값 객체 만들 예정
     */
    @PostMapping("/{playgroundId}/repo")
    public ResponseEntity<?> createPlaygroundGithubRepo(
        @PathVariable Long playgroundId,
        @Valid @RequestBody String repoTitle) {

//        return ResponseEntity.ok()
//            .body(new ApiResponse<>(playgroundService.createPlaygroundGithubRepo(playgroundId, repoTitle)));
        return ResponseEntity.ok().body(playgroundService.createPlaygroundGithubRepo(playgroundId, repoTitle));
    }

    /**
     * 생성된 Github Repo에 Playground Member를 Collaborator로 등록
     * TODO : 응답 값 및 에러 수정 필요
     */
    @PutMapping("/{playgroundId}/collaborators")
    public ResponseEntity<?> applyCollaborators(
        @PathVariable Long playgroundId,
        @Valid @RequestBody String repoTitle) {
        return ResponseEntity.ok().body(playgroundService.applyCollaboratorPlaygroundGithubRepo(playgroundId, repoTitle));
    }
}
