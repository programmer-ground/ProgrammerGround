package com.pg.chat.global.config.websocket;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WebSocketMessageEndPointProperty.class})
public class WebSocketPropertyConfiguration {
}
