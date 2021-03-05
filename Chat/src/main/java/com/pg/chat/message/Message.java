package com.pg.chat.message;

import java.util.Map;

import com.pg.chat.user.User;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message {
	private User user;
	private Map<String, Object> properties;

	@Builder
	public Message(User user, Map<String, Object> properties) {
		this.user = user;
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Message{" +
			"user=" + user +
			", properties=" + properties +
			'}';
	}
}
