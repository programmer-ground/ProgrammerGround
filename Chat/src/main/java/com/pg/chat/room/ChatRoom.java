package com.pg.chat.room;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;

@Getter
public class ChatRoom {
	private String roomId;
	private String title;
	private LocalDateTime createdDate;

	private ChatRoom() {
	}

	public static ChatRoom createNewChatRoom(final String title) {
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.roomId = UUID.randomUUID().toString();
		chatRoom.title = title;
		chatRoom.createdDate = LocalDateTime.now();
		return chatRoom;
	}

	@Override
	public String toString() {
		return "ChatRoom{" +
			"roomId='" + roomId + '\'' +
			", title='" + title + '\'' +
			", createdDate=" + createdDate +
			'}';
	}
}
