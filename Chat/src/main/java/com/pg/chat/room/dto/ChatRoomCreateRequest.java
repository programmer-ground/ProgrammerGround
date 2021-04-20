package com.pg.chat.room.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomCreateRequest {
	@NotNull
	@Positive
	private long playGroundId;
	@NotNull
	@Positive
	private long userId;
	@NotBlank
	private String title;

	@Override
	public String toString() {
		return "ChatRoomCreateRequest{" +
			"playGroundId=" + playGroundId +
			", userId=" + userId +
			", title='" + title + '\'' +
			'}';
	}
}
