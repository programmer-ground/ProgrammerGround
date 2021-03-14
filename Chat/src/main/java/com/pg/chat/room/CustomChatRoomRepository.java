package com.pg.chat.room;

import java.util.List;

public interface CustomChatRoomRepository {
	List<ChatRoom> findAllChatRoom();
	ChatRoom findByChatRoomId(String chatRoomId);
	ChatRoom saveNewChatRoom(String title);
}
