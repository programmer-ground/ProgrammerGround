package com.pg.chat.room.application;

import static com.pg.chat.room.domain.Role.*;
import static com.pg.chat.room.mapper.RoomMapper.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pg.chat.global.error.ErrorCode;
import com.pg.chat.room.dao.RoomRepository;
import com.pg.chat.room.domain.Member;
import com.pg.chat.room.domain.Role;
import com.pg.chat.room.domain.Room;
import com.pg.chat.room.dto.request.ChatRoomCreateRequest;
import com.pg.chat.room.dto.request.NewMemberJoinRequest;
import com.pg.chat.room.dto.request.RoomMemberKickOutRequest;
import com.pg.chat.room.dto.response.NewChatRoomCreateResponse;
import com.pg.chat.room.dto.response.RoomInfoResponse;
import com.pg.chat.room.dto.response.RoomMemberKickOutResponse;
import com.pg.chat.room.exception.DuplicateMemberJoinException;
import com.pg.chat.room.exception.RoomDuplicateException;
import com.pg.chat.room.exception.RoomManagementPermissionDeniedException;
import com.pg.chat.room.exception.RoomNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoomCommandService {
	private final RoomRepository roomRepository;

	public NewChatRoomCreateResponse createNewRoom(ChatRoomCreateRequest roomCreateRequest) {
		log.info("[CreateNewRoom]-[REQUEST]-[{}]", roomCreateRequest);
		boolean isAlreadyRegistered = roomRepository.existsByMasterId(roomCreateRequest.getUserId());
		if (isAlreadyRegistered) {
			throw new RoomDuplicateException(ErrorCode.ERR_CHAT_ROOM_ALREADY_EXITS);
		}

		Room room = Room.createNewRoom()
			.masterId(roomCreateRequest.getUserId())
			.playGroundId(roomCreateRequest.getPlayGroundId())
			.title(roomCreateRequest.getTitle())
			.build();

		addNewMember(room, roomCreateRequest.getUserId(), MASTER);

		roomRepository.save(room);

		log.info("[CreateNewRoom]-[SUCCESS]-[{}]", room);
		return ROOM_MAPPER.toNewChatRoomResponse(room);
	}

	public RoomInfoResponse addNewMemberInRoom(String roomId, NewMemberJoinRequest newMemberJoinRequest) {
		Room room = findRoomInfoByRoomId(roomId);

		if (room.memberExist(newMemberJoinRequest.getMemberId())) {
			throw new DuplicateMemberJoinException(ErrorCode.ERR_MEMBER_JOIN_DUPLICATE);
		}

		addNewMember(room, newMemberJoinRequest.getMemberId(), Role.valueOf(newMemberJoinRequest.getMemberRole()));

		roomRepository.save(room);

		return ROOM_MAPPER.toRoomInfoResponse(room);
	}

	private void addNewMember(Room room, long memberId, Role memberRole) {
		Member member = Member.createNewMember()
			.memberId(memberId)
			.role(memberRole)
			.build();
		room.joinNewMember(member);
	}

	public RoomMemberKickOutResponse roomMemberKickOut(
		String roomId, RoomMemberKickOutRequest roomMemberKickOutRequest
	) {

		Room room = findRoomInfoByRoomId(roomId);

		if (!hasRoomManagementPermission(room, roomMemberKickOutRequest.getMasterUserId())) {
			throw new RoomManagementPermissionDeniedException(ErrorCode.ERR_PERMISSION_DENIED);
		}

		boolean kickOutResult = room.kickOutMemberByMemberId(roomMemberKickOutRequest.getKickOutUserId());
		roomRepository.save(room);

		RoomInfoResponse roomInfoResponse = ROOM_MAPPER.toRoomInfoResponse(room);

		return new RoomMemberKickOutResponse(kickOutResult, roomInfoResponse);
	}

	private Room findRoomInfoByRoomId(String roomId) {
		return roomRepository.findById(roomId)
			.orElseThrow(() -> new RoomNotFoundException(ErrorCode.ERR_ROOM_NOT_FOUND));
	}

	private boolean hasRoomManagementPermission(Room room, long permissionCheckUserId) {
		return room.getMasterId() == permissionCheckUserId;
	}
}
