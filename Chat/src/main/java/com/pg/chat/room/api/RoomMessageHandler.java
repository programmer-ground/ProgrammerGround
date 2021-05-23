package com.pg.chat.room.api;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import com.pg.chat.global.config.amqp.RabbitMQConfiguration;
import com.pg.chat.room.dto.message.ChatMessage;
import com.pg.chat.room.application.MessageHandlerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RoomMessageHandler {
	private final MessageHandlerService messageHandlerService;

	@MessageMapping("/room/{roomId}")
	public void roomMessage(@Payload ChatMessage message, @DestinationVariable String roomId) {
		messageHandlerService.receiveMessageFromUser(message, roomId);
	}

	@RabbitListener(queues = RabbitMQConfiguration.QUEUE_NAME)
	public void getMessageFromRabbitMq(Message message) {
		messageHandlerService.rabbitmqMessageSubscriber(message);
	}
}
