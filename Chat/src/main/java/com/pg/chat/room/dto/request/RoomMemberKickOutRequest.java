package com.pg.chat.room.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomMemberKickOutRequest {
	@NotNull
	@Positive
	private long masterUserId;
	@NotNull
	@Positive
	private long kickOutUserId;

	@Override
	public String toString() {
		return "RoomMemberKickOutRequest{" +
			"masterUserId=" + masterUserId +
			", kickOutUserId=" + kickOutUserId +
			'}';
	}
}
