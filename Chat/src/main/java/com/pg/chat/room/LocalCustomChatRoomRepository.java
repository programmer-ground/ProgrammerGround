package com.pg.chat.room;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile(value = {"!production", "!staging"})
@Repository
public class LocalCustomChatRoomRepository implements CustomChatRoomRepository {
	private Map<String, ChatRoom> chatRoomMap;

	@PostConstruct
	public void init() {
		this.chatRoomMap = new ConcurrentHashMap<>();
	}

	@Override
	public List<ChatRoom> findAllChatRoom() {
		return new ArrayList<>(this.chatRoomMap.values());
	}

	@Override
	public ChatRoom findByChatRoomId(String chatRoomId) {
		return this.chatRoomMap.get(chatRoomId);
	}

	@Override
	public ChatRoom saveNewChatRoom(String title) {
		ChatRoom chatRoom = ChatRoom.createNewChatRoom(title);
		log.info("Create New ChatRoom - {}", chatRoom);
		this.chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
		return chatRoom;
	}
}
