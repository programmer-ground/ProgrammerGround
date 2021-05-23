package com.pg.chat.room.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.chat.global.common.ApiResponse;
import com.pg.chat.global.common.LogFormat;
import com.pg.chat.global.common.PagingRequest;
import com.pg.chat.global.error.ErrorCode;
import com.pg.chat.global.error.exception.InvalidParameterException;
import com.pg.chat.room.application.RoomQueryService;
import com.pg.chat.room.dto.MyRoomInfoListResponse;

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
		@RequestParam(value = "userId", defaultValue = "0") long userId, PagingRequest pagingRequest
	) {
		if (userId <= 0) {
			log.error(LogFormat.PARAMETER_ERROR_LOG, "invalid search condition of [userId]");
			throw new InvalidParameterException(ErrorCode.ERR_INVALID_REQUEST_PARAMETER);
		}
		MyRoomInfoListResponse response = roomQueryService.getMyRoomInfoList(userId, pagingRequest.of());
		return ResponseEntity.ok(new ApiResponse<>(response));
	}
}
