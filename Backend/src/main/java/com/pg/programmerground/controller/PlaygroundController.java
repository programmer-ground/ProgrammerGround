package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.playground.api_req.ApplyPlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.RevisePlaygroundApi;
import com.pg.programmerground.dto.playground.response.PlaygroundCardListResponse;
import com.pg.programmerground.dto.playground.response.PlaygroundResponse;
import com.pg.programmerground.dto.playground.response.PlaygroundResultResponse;
import com.pg.programmerground.service.PlaygroundService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    /**
     * playground 생성
     */
    @PostMapping("")
    public ResponseEntity<ApiResponse<Long>> createPlayground(@Valid @RequestBody PlaygroundApi info) throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.createPlayground(info)));
    }

    @ApiOperation(value = "Playground 참가 신청", notes = "Playground 참가 신청 요청")
    @PostMapping("/{playgroundId}/apply")
    public ResponseEntity<ApiResponse<PlaygroundResultResponse>> applyPlayground(@PathVariable Long playgroundId, @RequestBody ApplyPlaygroundApi applyPlayground) throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(new PlaygroundResultResponse(playgroundService.applyPlayground(playgroundId, applyPlayground))));
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
    public ResponseEntity<ApiResponse<Integer>> revisePlayground(@Valid RevisePlaygroundApi info, @PathVariable Long playgroundId) {
        return ResponseEntity.accepted().body(new ApiResponse<>(null));
    }

    @ApiOperation(value = "Playground 삭제", notes = "Playground 삭제 요청")
    @DeleteMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<Integer>> deletePlayground(@PathVariable Long playgroundId) {
        return ResponseEntity.ok().body(new ApiResponse<>(null));
    }
}
