package com.pg.chat.room.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RoomMemberKickOutResponse {
	private final boolean kickOutResult;
	private final RoomInfoResponse updatedRoomInfo;

	@Override
	public String toString() {
		return "RoomMemberKickOutResponse{" +
			"kickOutResult=" + kickOutResult +
			", updatedRoomInfo=" + updatedRoomInfo +
			'}';
	}
}
