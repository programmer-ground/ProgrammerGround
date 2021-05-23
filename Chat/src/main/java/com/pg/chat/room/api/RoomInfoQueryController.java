package com.pg.chat.room.api;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.chat.global.common.ApiResponse;
import com.pg.chat.global.common.PagingRequest;
import com.pg.chat.room.application.RoomQueryService;
import com.pg.chat.room.dto.response.MyRoomInfoListResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomInfoQueryController {
	private final RoomQueryService roomQueryService;

	/**
	 * 내 채팅방 조회
	 * @param userId - 조회 사용자 고유 식별자
	 * @param pagingRequest - 페이징 요청 정보
	 * @return - 내 채팅방 정보 목록
	 */
	@GetMapping()
	public ResponseEntity<ApiResponse<MyRoomInfoListResponse>> myRoomInfoListResponse(
		@RequestParam(value = "userId", defaultValue = "0") @Positive @Valid long userId, PagingRequest pagingRequest
	) {
		MyRoomInfoListResponse response = roomQueryService.getMyRoomInfoList(userId, pagingRequest.of());
		return ResponseEntity.ok(new ApiResponse<>(response));
	}
}
