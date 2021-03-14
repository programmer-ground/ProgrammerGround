package com.pg.chat.config.websocket;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@ConfigurationProperties(prefix = "message")
public class WebSocketMessageEndPointProperty {
	private String webSocketPath = "/chat";
	private String messageSubscribeContextPath = "/sub";
	private String messagePublishContextPath = "/pub";

	@Override
	public String toString() {
		return "MessageEndPointProperty{" +
			"webSocketPath='" + webSocketPath + '\'' +
			", messageSubscribeContextPath='" + messageSubscribeContextPath + '\'' +
			", messagePublishContextPath='" + messagePublishContextPath + '\'' +
			'}';
	}
}
