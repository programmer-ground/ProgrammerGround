package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.playground.api_req.ApplyPlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.RevisePlaygroundApi;
import com.pg.programmerground.dto.playground.response.PlaygroundCardResponse;
import com.pg.programmerground.dto.playground.response.PlaygroundResponse;
import com.pg.programmerground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ApiResponse<List<PlaygroundCardResponse>>> playgroundList() {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.getPlaygroundCardList()));
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
    @PostMapping("/apply/{playgroundId}")
    public ResponseEntity<ApiResponse<Boolean>> applyPlayground(@PathVariable Long playgroundId, @RequestBody ApplyPlaygroundApi applyPlayground) throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.applyPlayground(playgroundId, applyPlayground)));
    }

    /**
     * playground 신청 수락
     */
    @PutMapping("/apply/{playgroundApplyId}")
    public ResponseEntity<ApiResponse<Boolean>> acceptPlayground(@PathVariable Long playgroundApplyId) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.acceptPlayground(playgroundApplyId)));
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
}
