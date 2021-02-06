package com.pg.chat.user;

import static com.pg.chat.event.EventType.*;
import static java.util.Arrays.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import com.pg.chat.event.Event;
import com.pg.chat.event.EventType;
import com.pg.chat.message.Message;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

public class UserStats {
	private final UnicastProcessor eventPublisher;
	private Map<String, Stats> userStatsMap = new ConcurrentHashMap<>();

	public UserStats(Flux<Event> events, UnicastProcessor eventPublisher) {
		this.eventPublisher = eventPublisher;

		events.filter(type(CHAT_MESSAGE, USER_JOINED))
			.subscribe(this::onChatMessage);

		events.filter(type(USER_LEFT))
			.map(Event::getUser)
			.map(User::getNickname)
			.subscribe(userStatsMap::remove);

		events.filter(type(USER_JOINED))
			.map(event -> {
				User user = User.builder()
					.nickname("SYSTEM_USER")
					.profile("default")
					.build();

				Map<String, Object> property = new HashMap<>();
				property.put("stats", new HashMap<>(userStatsMap));

				Message message = Message.builder()
					.user(user)
					.properties(property)
					.build();
				return Event.builder()
					.eventType(USER_STATS)
					.message(message)
					.build();
			}).subscribe(eventPublisher::onNext);
	}

	private static Predicate<Event> type(EventType... types) {
		return event -> asList(types).contains(event.getType());
	}

	private void onChatMessage(Event event) {
		String nickname = event.getUser().getNickname();
		Stats stats = userStatsMap.computeIfAbsent(nickname, s -> new Stats(event.getUser()));
		stats.onChatMessage(event);
	}
}
