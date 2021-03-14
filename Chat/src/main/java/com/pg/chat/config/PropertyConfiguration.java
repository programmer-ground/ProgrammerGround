package com.pg.chat.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MessageEndPointProperty.class})
public class PropertyConfiguration {
}
