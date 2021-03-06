package com.pg.chat.event;

import java.time.LocalDateTime;
import java.util.UUID;

import com.pg.chat.message.Message;
import com.pg.chat.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
	private EventType type;
	private String id;
	private Message message;
	private LocalDateTime timestamp;

	protected Event() {
		this.id = UUID.randomUUID().toString();
		this.timestamp = LocalDateTime.now();
	}

	@Builder
	public Event(EventType eventType, Message message) {
		this.id = UUID.randomUUID().toString();
		this.type = eventType;
		this.message = message;
		this.timestamp = LocalDateTime.now();
	}

	public User getUser() {
		return this.message.getUser();
	}

	@Override
	public String toString() {
		return "Event{" +
			"type=" + type +
			", id='" + id + '\'' +
			", message=" + message +
			", timestamp=" + timestamp +
			'}';
	}
}
