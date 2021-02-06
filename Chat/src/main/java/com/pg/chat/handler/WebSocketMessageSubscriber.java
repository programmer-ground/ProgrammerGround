package com.pg.chat.handler;

import static com.pg.chat.event.EventType.*;

import java.util.Optional;

import com.pg.chat.event.Event;
import com.pg.chat.message.Message;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.UnicastProcessor;

@Slf4j
public class WebSocketMessageSubscriber {
	private final UnicastProcessor<Event> eventPublisher;
	private Optional<Event> lastReceivedEvent = Optional.empty();

	public WebSocketMessageSubscriber(UnicastProcessor<Event> eventPublisher) {
		this.eventPublisher = eventPublisher;
	}

	public void onNext(Event event) {
		lastReceivedEvent = Optional.of(event);
		log.info("[ON_NEXT] - {}", event);
		eventPublisher.onNext(event);
	}

	public void onError(Throwable error) {
		log.error("[ON_ERROR] - {}", error.getMessage());
		error.printStackTrace();
	}

	public void onComplete() {
		lastReceivedEvent.ifPresent(event -> {
			Message message = Message.builder()
				.user(event.getUser())
				.build();
			Event completeEvent = Event.builder()
				.eventType(USER_LEFT)
				.message(message)
				.build();
			log.info("[ON_COMPLETE] - {}", completeEvent);
			eventPublisher.onNext(completeEvent);
		});
	}
}
