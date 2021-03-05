package com.pg.chat.user;

import static com.pg.chat.event.EventType.*;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

import com.pg.chat.event.Event;

public class Stats {
	private final User user;
	private LocalDateTime lastMessage;
	private AtomicInteger messageCount = new AtomicInteger();

	public Stats(User user) {
		this.user = user;
	}

	public void onChatMessage(Event event) {
		lastMessage = event.getTimestamp();
		if (CHAT_MESSAGE == event.getType()) {
			messageCount.incrementAndGet();
		}
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getLastMessage() {
		return lastMessage;
	}

	public int getMessageCount() {
		return messageCount.get();
	}
}
