package com.pg.chat.room;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.pg.chat.config.MessageEndPointProperty;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RoomMessageHandler {
	private final MessageEndPointProperty messageEndPointProperty;
	private final SimpMessageSendingOperations messageOperation;

	@MessageMapping("/room/{roomId}")
	@SendTo("/room/{roomId}")
	public ChatMessage roomMessage(@Payload ChatMessage message, @DestinationVariable String roomId) {
		log.info("RECEIVE: {} , Topic Routing Path: [{}]", message.toString(), roomId);
		message.setRoomId(roomId);
		return message;
	}
}
