package com.pg.chat.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.reactive.socket.server.upgrade.ReactorNettyRequestUpgradeStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.chat.event.Event;
import com.pg.chat.handler.ChattingMessageHandler;
import com.pg.chat.user.UserStats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebSocketConfiguration {
	private static final String WEB_SOCKET_PATH = "/chat/message";
	private final ObjectMapper objectMapper;

	@Bean
	public UnicastProcessor<Event> eventPublisher() {
		return UnicastProcessor.create();
	}

	@Bean
	public Flux<Event> events(UnicastProcessor<Event> eventPublisher) {
		return eventPublisher
			.replay(25)
			.autoConnect();
	}

	@Bean
	public HandlerMapping webSocketMapping(UnicastProcessor<Event> eventPublisher, Flux<Event> events) {
		Map<String, Object> map = new HashMap<>();
		map.put(WEB_SOCKET_PATH, new ChattingMessageHandler(eventPublisher, events, objectMapper));
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		simpleUrlHandlerMapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
		simpleUrlHandlerMapping.setUrlMap(map);

		log.info("[WEB_SOCKET_PATH] - {}", WEB_SOCKET_PATH);
		return simpleUrlHandlerMapping;
	}

	@Bean
	public WebSocketHandlerAdapter handlerAdapter(WebSocketService webSocketService) {
		return new WebSocketHandlerAdapter(webSocketService);
	}

	@Bean
	public UserStats userStats(Flux<Event> events, UnicastProcessor<Event> eventPublisher) {
		return new UserStats(events, eventPublisher);
	}

	@Bean
	public WebSocketService webSocketService() {
		return new HandshakeWebSocketService(new ReactorNettyRequestUpgradeStrategy());
	}
}
