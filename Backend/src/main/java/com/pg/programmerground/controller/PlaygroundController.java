package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.playground.MakePlaygroundInfoDto;
import com.pg.programmerground.dto.playground.PlaygroundCardInfoDto;
import com.pg.programmerground.dto.playground.PlaygroundInfoDto;
import com.pg.programmerground.dto.playground.RevisePlaygroundInfoDto;
import com.pg.programmerground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ApiResponse<Integer>> makePlayground(@Valid MakePlaygroundInfoDto info) {
        return ResponseEntity.ok().body(new ApiResponse<>(null));
    }

    /**
     * playground 상세 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PlaygroundInfoDto>> getPlayground(@PathVariable String id) {
        return ResponseEntity.ok().body(new ApiResponse<>(null));
    }

    /**
     * playground 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> revisePlayground(@Valid RevisePlaygroundInfoDto info, @PathVariable Long id) {
        return ResponseEntity.accepted().body(new ApiResponse<>(null));
    }

    /**
     * playground 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Integer>> deletePlayground(@PathVariable String id) {
        return ResponseEntity.ok().body(new ApiResponse<>(null));
    }
}
