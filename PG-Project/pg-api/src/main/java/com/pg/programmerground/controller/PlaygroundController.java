package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.pgp.domain.enumerated.Position;
import com.pg.pgp.dto.playground.PlaygroundPositionsDto;
import com.pg.pgp.dto.playground.api_req.ApplyPlaygroundApi;
import com.pg.pgp.dto.playground.api_req.PlaygroundApi;
import com.pg.pgp.dto.playground.api_req.RevisePlaygroundApi;
import com.pg.pgp.dto.playground.response.PlaygroundCardListResponse;
import com.pg.pgp.dto.playground.response.PlaygroundResponse;
import com.pg.pgp.dto.playground.response.PlaygroundResultResponse;
import com.pg.programmerground.service.PlaygroundService;
import com.pg.pgp.vo.GithubRepoVo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/playground")
public class PlaygroundController {

    private final PlaygroundService playgroundService;

    /**
     * 무한 스크롤
     */
    @ApiOperation(value = "Playground 리스트 정보", notes = "Playground 리스트 정보 요청")
    @GetMapping("")
    public ResponseEntity<ApiResponse<PlaygroundCardListResponse>> playgroundList() {
        return ResponseEntity.ok().body(new ApiResponse<>(new PlaygroundCardListResponse(playgroundService.getPlaygroundCardList())));
    }

    @ApiOperation(value = "Playground 생성", notes = "Playground 생성 요청")
    @PostMapping("")
    public ResponseEntity<ApiResponse<Long>> createPlayground(@RequestPart(required = false) MultipartFile mainImg, @RequestPart @Validated PlaygroundApi info) throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.createPlayground(mainImg, info)));
    }

    @ApiOperation(value = "PlaygroundPosition 리스트 정보", notes = "PlaygroundPosition 리스트 정보 요청")
    @GetMapping("/{playgroundId}/slots")
    public ResponseEntity<ApiResponse<PlaygroundPositionsDto>> playgroundPositions(@PathVariable Long playgroundId) {
        return ResponseEntity.ok()
                .body(new ApiResponse<>(playgroundService.getPlaygroundPositions(playgroundId)));
    }

    @ApiOperation(value = "Playground 참가 신청", notes = "Playground 참가 신청 요청")
    @PostMapping("/{playgroundId}/apply")
    public ResponseEntity<ApiResponse<Long>> applyPlayground(@PathVariable Long playgroundId, @RequestBody @Validated ApplyPlaygroundApi applyPlayground) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.applyPlayground(playgroundId, applyPlayground)));
    }

    @ApiOperation(value = "Playground 참가 취소", notes = "Playground 참가 취소")
    @PutMapping("/{playgroundApplyId}/cancel")
    public ResponseEntity<ApiResponse<PlaygroundResultResponse>> cancelPlayground(@PathVariable Long playgroundApplyId) {
        return ResponseEntity.ok().body(new ApiResponse<>(new PlaygroundResultResponse(playgroundService.cancelPlayground(playgroundApplyId))));
    }

    @ApiOperation(value = "Playground 참기 신청 수락", notes = "Playground Leader가 참가 신청 수락 요청")
    @PutMapping("/applicants/{playgroundApplyId}/accept")
    public ResponseEntity<ApiResponse<PlaygroundResultResponse>> acceptPlaygroundApply(@PathVariable Long playgroundApplyId) {
        return ResponseEntity.ok().body(new ApiResponse<>(new PlaygroundResultResponse(playgroundService.acceptPlayground(playgroundApplyId))));
    }

    @ApiOperation(value = "Playground 참가 신청 거절", notes = "Playground Leader가 참가 신청 거절 요청")
    @PutMapping("/applicants/{playgroundApplyId}/reject")
    public ResponseEntity<ApiResponse<Boolean>> rejectPlaygroundApply(@PathVariable Long playgroundApplyId) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.rejectPlayground(playgroundApplyId)));
    }

    @ApiOperation(value = "Playground 상세 정보", notes = "Playground 상세 정보 요청")
    @GetMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<PlaygroundResponse>> getPlayground(@PathVariable Long playgroundId) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.getPlaygroundDetailInfo(playgroundId)));
    }

    @ApiOperation(value = "Playground 수정", notes = "Playgorund 수정 요청")
    @PutMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<Integer>> revisePlayground(@Validated RevisePlaygroundApi info, @PathVariable Long playgroundId) {
        return ResponseEntity.accepted().body(new ApiResponse<>(null));
    }

    @ApiOperation(value = "Playground 삭제", notes = "Playground 삭제 요청")
    @DeleteMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<Boolean>> deletePlayground(@PathVariable Long playgroundId) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.removePlayground(playgroundId)));
    }

    /**
     * Playground 레포 생성 요청
     * TODO : 응답 값 객체 만들 예정
     */
    @PostMapping("/{playgroundId}/repo")
    public ResponseEntity<?> createPlaygroundGithubRepo(
        @PathVariable Long playgroundId,
        @Valid @RequestBody GithubRepoVo githubRepoVo) {
        return ResponseEntity.ok()
                .body(playgroundService.createPlaygroundGithubRepo(playgroundId, githubRepoVo));
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

    @ApiOperation(value = "참여 신청할 Position 리스트 정보", notes = "PlaygroundPostion 리스트 정보 요청")
    @GetMapping("/positions")
    public ResponseEntity<?> positions() {
        return ResponseEntity.ok().body(Position.toEntity());
    }
}
