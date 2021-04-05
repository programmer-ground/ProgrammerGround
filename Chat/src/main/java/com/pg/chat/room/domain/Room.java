package com.pg.chat.room.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"id","roomMasterId"})
@Document(collation = "room")
public class Room {
	@Id
	private String id;

	@Indexed
	private Long playgroundId;

	private String title;

	private Long roomMasterId;

	@Builder(builderMethodName = "createNewRoom")
	Room(Long playgroundId, String title, Long roomMasterId) {
		this.id = UUID.randomUUID().toString();
		this.playgroundId = playgroundId;
		this.title = title;
		this.roomMasterId = roomMasterId;
	}

	@Override
	public String toString() {
		return "Room{" +
			"id='" + id + '\'' +
			", playgroundId=" + playgroundId +
			", title='" + title + '\'' +
			", roomMasterId=" + roomMasterId +
			'}';
	}
}
