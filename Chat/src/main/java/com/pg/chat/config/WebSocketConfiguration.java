package com.pg.chat.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
	private final MessageEndPointProperty messageEndPointProperty;

	@PostConstruct
	public void init() {
		log.info("WebSocketConfiguration - Properties:: {} ",messageEndPointProperty);
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker(messageEndPointProperty.getMessageSubscribeContextPath());
		registry.setApplicationDestinationPrefixes(messageEndPointProperty.getMessagePublishContextPath());
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(messageEndPointProperty.getWebSocketPath())
			.setAllowedOrigins("*")
			.withSockJS();
	}
}
