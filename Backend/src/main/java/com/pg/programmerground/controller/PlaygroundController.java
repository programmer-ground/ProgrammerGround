package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.playground.MakePlaygroundInfoDto;
import com.pg.programmerground.dto.playground.PlaygroundCardInfoDto;
import com.pg.programmerground.dto.playground.PlaygroundInfoDto;
import com.pg.programmerground.dto.playground.RevisePlaygroundInfoDto;
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
    public ResponseEntity<ApiResponse<List<PlaygroundCardInfoDto>>> playgroundList() {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.getPlaygroundCardList()));
    }

    /**
     * playground 생성
     */
    @PostMapping("")
    public ResponseEntity<ApiResponse<Long>> makePlayground(@Valid @RequestBody MakePlaygroundInfoDto info) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.createPlayground(info)));
    }

    /**
     * playground 상세 정보
     */
    @GetMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<PlaygroundInfoDto>> getPlayground(@PathVariable Long playgroundId) {
        return ResponseEntity.ok().body(new ApiResponse<>(playgroundService.getPlaygroundDetailInfo(playgroundId)));
    }

    /**
     * playground 수정
     */
    @PutMapping("/{playgroundId}")
    public ResponseEntity<ApiResponse<Integer>> revisePlayground(@Valid RevisePlaygroundInfoDto info, @PathVariable Long playgroundId) {
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
     * playground 참가 신청
     */
    @PostMapping("/apply/{playgroundId}")
    public ResponseEntity<ApiResponse<Boolean>> applyPlayground(@PathVariable Long playgroundId) {
        return ResponseEntity.ok().body(new ApiResponse<>(null));
    }
}
