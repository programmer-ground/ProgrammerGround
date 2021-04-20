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
import com.pg.chat.room.dto.ChatRoomCreateRequest;
import com.pg.chat.room.dto.NewChatRoomCreateResponse;
import com.pg.chat.room.dto.NewMemberJoinRequest;
import com.pg.chat.room.dto.RoomInfoResponse;
import com.pg.chat.room.exception.DuplicateMemberJoinException;
import com.pg.chat.room.exception.RoomDuplicateException;
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

	private void addNewMember(Room room, long memberId, Role memberRole) {
		Member member = Member.createNewMember()
			.memberId(memberId)
			.role(memberRole)
			.build();
		room.joinNewMember(member);
	}

	public RoomInfoResponse addNewMemberInRoom(String roomId, NewMemberJoinRequest newMemberJoinRequest) {
		Room room = roomRepository.findById(roomId)
			.orElseThrow(() -> new RoomNotFoundException(ErrorCode.ERR_ROOM_NOT_FOUND));

		if (room.memberExist(newMemberJoinRequest.getMemberId(), Role.valueOf(newMemberJoinRequest.getMemberRole()))) {
			throw new DuplicateMemberJoinException(ErrorCode.ERR_MEMBER_JOIN_DUPLICATE);
		}

		addNewMember(room, newMemberJoinRequest.getMemberId(), Role.valueOf(newMemberJoinRequest.getMemberRole()));

		roomRepository.save(room);

		return ROOM_MAPPER.toRoomInfoResponse(room);
	}
}
