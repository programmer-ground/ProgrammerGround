package com.pg.chat.room.application;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pg.chat.global.config.amqp.RabbitMQConfiguration;
import com.pg.chat.global.config.websocket.WebSocketMessageEndPointProperty;
import com.pg.chat.room.dto.ChatMessage;
import com.pg.chat.room.exception.MessageRoutingKeyExplodeException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageHandlerService {
	private final String RABBITMQ_PUBLISH_LOG = "[RABBITMQ_PUBLISH_MESSAGE][{}] >> {}";
	private final String MESSAGE_PUBLISH_LOG = "[STOMP_MESSAGE_PUBLISH][{}] >> {}";
	private final String RABBITMQ_SUBSCRIBE_LOG = "[RABBITMQ_SUBSCRIBE_MESSAGE][{}] << {}";
	private final String MESSAGE_SUBSCRIBE_LOG = "[STOMP_MESSAGE_SUBSCRIBE][{}] << {}";

	private final RabbitTemplate rabbitMQOperationTemplate;
	private final WebSocketMessageEndPointProperty messageProperty;
	private final SimpMessageSendingOperations stompMessageOperation;

	public void receiveMessageFromUser(ChatMessage message, String roomId) {
		log.info("-------------------------------------------------------------------------------------------------");
		log.info(MESSAGE_SUBSCRIBE_LOG, messageProperty.getMessagePublishContextPath() + "/" + roomId, message);
		rabbitMQOperationTemplate.convertAndSend(
			RabbitMQConfiguration.TOPIC_EXCHANGE_NAME, "room." + roomId, message.toString());
		log.info(RABBITMQ_PUBLISH_LOG, RabbitMQConfiguration.TOPIC_EXCHANGE_NAME + " -> " + "room." + roomId, message);
		log.info("-------------------------------------------------------------------------------------------------");
	}

	public void rabbitmqMessageSubscriber(Message message) {
		String body = new String(message.getBody(), StandardCharsets.UTF_8);
		log.info("-------------------------------------------------------------------------------------------------");
		log.info(RABBITMQ_SUBSCRIBE_LOG, message.getMessageProperties().getReceivedRoutingKey(), body);
		String destination = getMessageSubscribeDestination(message.getMessageProperties().getReceivedRoutingKey());
		stompMessageOperation.convertAndSend(destination, body);
		log.info(MESSAGE_PUBLISH_LOG, destination, body);
		log.info("-------------------------------------------------------------------------------------------------");
	}

	private String getMessageSubscribeDestination(String messageRoutingKey) {
		String roomId = findRoomIdFromMessageRouteKey(messageRoutingKey);
		return String.format("%s/%s", messageProperty.getMessageSubscribeContextPath(), roomId);
	}

	private String findRoomIdFromMessageRouteKey(String routeKey) {
		if (StringUtils.isEmpty(routeKey) || routeKey.split("\\.").length < 2) {
			throw new MessageRoutingKeyExplodeException(routeKey);
		}
		return routeKey.split("\\.")[1];
	}
}
