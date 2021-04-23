package com.pg.chat.room.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pg.chat.global.common.ApiResponse;
import com.pg.chat.global.common.LogFormat;
import com.pg.chat.global.common.PagingRequest;
import com.pg.chat.global.error.ErrorCode;
import com.pg.chat.global.error.exception.InvalidParameterException;
import com.pg.chat.room.application.RoomCommandService;
import com.pg.chat.room.application.RoomQueryService;
import com.pg.chat.room.dto.ChatRoomCreateRequest;
import com.pg.chat.room.dto.MyRoomInfoListResponse;
import com.pg.chat.room.dto.NewChatRoomCreateResponse;
import com.pg.chat.room.dto.NewMemberJoinRequest;
import com.pg.chat.room.dto.RoomInfoResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomApiController {
	private final RoomQueryService roomQueryService;
	private final RoomCommandService roomCommandService;

	/**
	 * 신규 채팅방 정보 생성
	 * @param roomCreateRequest - 신규 채팅방 정보 (플레이그라운드 고유 식별자 정보, 마스터 고유 식별자, 채팅방 타이틀 정보)
	 * @param result - 요청 파라미터 검증 결과
	 * @return - 신규 채팅방 정보
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<NewChatRoomCreateResponse>> createNewChatRoomRequest(
		@RequestBody @Valid ChatRoomCreateRequest roomCreateRequest, BindingResult result
	) {
		log.info("request: [{}]", roomCreateRequest);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(message -> log.error(LogFormat.PARAMETER_ERROR_LOG, message));
			throw new InvalidParameterException(ErrorCode.ERR_INVALID_REQUEST_PARAMETER);
		}
		NewChatRoomCreateResponse response = roomCommandService.createNewRoom(roomCreateRequest);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	/**
	 * 채팅방에 사용자 추가
	 * @param roomId - 채팅방 아이디
	 * @param newMemberJoinRequest - 추가할 사용자 정보 (사용자 고유 식별자, 역할)
	 * @param result - 요청 파라미터 검증 결과
	 * @return - 신규 채팅방 정보 ( 신규 사용자 추가 정보 및 기존 채팅방 정보 )
	 */
	@PostMapping("/{roomId}/members")
	public ResponseEntity<ApiResponse<RoomInfoResponse>> addNewMemberInRoom(
		@PathVariable("roomId") String roomId, @RequestBody @Valid NewMemberJoinRequest newMemberJoinRequest, BindingResult result
	) {
		log.info("request: [{}]", newMemberJoinRequest);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(message -> log.error(LogFormat.PARAMETER_ERROR_LOG, message));
			throw new InvalidParameterException(ErrorCode.ERR_INVALID_REQUEST_PARAMETER);
		}
		RoomInfoResponse response = roomCommandService.addNewMemberInRoom(roomId, newMemberJoinRequest);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	/**
	 * 내 채팅방 조회
	 * @param userId - 조회 사용자 고유 식별자
	 * @param pagingRequest - 페이징 요청 정보
	 * @return - 내 채팅방 정보 목록
	 */
	@GetMapping()
	public ResponseEntity<ApiResponse<MyRoomInfoListResponse>> myRoomInfoListResponse(
		@RequestParam(value = "userId", defaultValue = "0") long userId, PagingRequest pagingRequest
	){
		if(userId <= 0){
			log.error(LogFormat.PARAMETER_ERROR_LOG, "invalid search condition of [userId]");
			throw new InvalidParameterException(ErrorCode.ERR_INVALID_REQUEST_PARAMETER);
		}
		MyRoomInfoListResponse response = roomQueryService.getMyRoomInfoList(userId, pagingRequest.of());
		return ResponseEntity.ok(new ApiResponse<>(response));
	}
}
