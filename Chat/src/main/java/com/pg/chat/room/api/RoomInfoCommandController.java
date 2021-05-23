package com.pg.chat.room.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.chat.global.common.ApiResponse;
import com.pg.chat.room.application.RoomCommandService;
import com.pg.chat.room.dto.request.ChatRoomCreateRequest;
import com.pg.chat.room.dto.request.NewMemberJoinRequest;
import com.pg.chat.room.dto.request.RoomMemberKickOutRequest;
import com.pg.chat.room.dto.response.NewChatRoomCreateResponse;
import com.pg.chat.room.dto.response.RoomInfoResponse;
import com.pg.chat.room.dto.response.RoomMemberKickOutResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomInfoCommandController {
	private final RoomCommandService roomCommandService;

	/**
	 * 신규 채팅방 정보 생성
	 * @param roomCreateRequest - 신규 채팅방 정보 (플레이그라운드 고유 식별자 정보, 마스터 고유 식별자, 채팅방 타이틀 정보)
	 * @return - 신규 채팅방 정보
	 */
	@PostMapping
	public ResponseEntity<ApiResponse<NewChatRoomCreateResponse>> createNewChatRoomRequest(
		@RequestBody @Valid ChatRoomCreateRequest roomCreateRequest
	) {
		NewChatRoomCreateResponse response = roomCommandService.createNewRoom(roomCreateRequest);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	/**
	 * 채팅방에 사용자 추가
	 * @param roomId - 채팅방 아이디
	 * @param newMemberJoinRequest - 추가할 사용자 정보 (사용자 고유 식별자, 역할)
	 * @return - 신규 채팅방 정보 ( 신규 사용자 추가 정보 및 기존 채팅방 정보 )
	 */
	@PostMapping("/{roomId}/members")
	public ResponseEntity<ApiResponse<RoomInfoResponse>> addNewMemberInRoom(
		@PathVariable("roomId") String roomId, @RequestBody @Valid NewMemberJoinRequest newMemberJoinRequest
	) {
		RoomInfoResponse response = roomCommandService.addNewMemberInRoom(roomId, newMemberJoinRequest);
		return ResponseEntity.ok(new ApiResponse<>(response));
	}

	/**
	 * 채팅방에서 멤버 내보내기
	 * @param roomId - 채팅방 식별자
	 * @param roomMemberKickOutRequest - 멤버 내보내기 요청 정보
	 * @return - 멤버 내보내기 처리 결과
	 */
	@DeleteMapping("/{roomId}/members")
	public ResponseEntity<ApiResponse<RoomMemberKickOutResponse>> roomMemberKickOut(
		@PathVariable("roomId") String roomId, @RequestBody @Valid RoomMemberKickOutRequest roomMemberKickOutRequest
	) {
		RoomMemberKickOutResponse kickOutResponse = roomCommandService.roomMemberKickOut(
			roomId,
			roomMemberKickOutRequest
		);
		return ResponseEntity.ok(new ApiResponse<>(kickOutResponse));
	}
}
