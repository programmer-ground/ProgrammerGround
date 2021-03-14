package com.pg.chat.room;

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
		return "ChatMessage{" +
			"roomId='" + roomId + '\'' +
			", senderId='" + senderId + '\'' +
			", senderName='" + senderName + '\'' +
			", contents='" + contents + '\'' +
			'}';
	}
}
