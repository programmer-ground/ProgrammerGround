package com.pg.chat.room.dto.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
	private String roomId;
	private String senderId;
	private String senderName;
	private String contents;

	@Override
	public String toString() {
		return "{" +
			"roomId='" + roomId + '\'' +
			", senderId='" + senderId + '\'' +
			", senderName='" + senderName + '\'' +
			", contents='" + contents + '\'' +
			'}';
	}
}
