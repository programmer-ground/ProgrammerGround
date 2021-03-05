package com.pg.chat.handler;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.chat.event.Event;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.UnicastProcessor;

@Slf4j
public class ChattingMessageHandler implements WebSocketHandler {
	private final UnicastProcessor<Event> eventPublisher;
	private final Flux<String> outputEvent;
	private final ObjectMapper objectMapper;

	public ChattingMessageHandler(
		UnicastProcessor<Event> eventPublisher, Flux<Event> events, ObjectMapper objectMapper
	) {
		this.eventPublisher = eventPublisher;
		this.objectMapper = objectMapper;
		this.outputEvent = Flux.from(events).map(this::toJson);

		log.info("[CHATTING_MESSAGE_HANDLER] - INITIALIZED");
	}

	@Override
	public Mono<Void> handle(WebSocketSession session) {
		log.info("[{}] - {}", session.getId(), session.getHandshakeInfo().toString());
		WebSocketMessageSubscriber subscriber = new WebSocketMessageSubscriber(eventPublisher);
		return session.receive()
			.map(WebSocketMessage::getPayloadAsText)
			.map(this::toEvent)
			.doOnNext(subscriber::onNext)
			.doOnError(subscriber::onError)
			.doOnComplete(subscriber::onComplete)
			.zipWith(session.send(outputEvent.map(session::textMessage)))
			.then();
	}

	private String toJson(Event event) {
		try {
			return objectMapper.writeValueAsString(event);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private Event toEvent(String jsonEvent) {
		try {
			return objectMapper.readValue(jsonEvent, Event.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("[INVALID_JSON]: " + jsonEvent, e);
		}
	}

}
