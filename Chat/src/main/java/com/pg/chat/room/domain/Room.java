package com.pg.chat.room.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = {"id", "masterId"})
@Document(collation = "room")
public class Room {
	@Id
	private String id;

	@Indexed
	private Long playgroundId;

	private String title;

	private Long masterId;

	private Set<Member> members = new HashSet<>();

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	@Builder(builderMethodName = "createNewRoom")
	Room(Long playgroundId, String title, Long masterId) {
		// this.id = UUID.randomUUID().toString();
		this.playgroundId = playgroundId;
		this.title = title;
		this.masterId = masterId;
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}

	public boolean joinNewMember(Member member) {
		return this.members.add(member);
	}

	public boolean kickOutMember(Member member) {
		return this.members.remove(member);
	}

	public boolean kickOutMemberByMemberId(Long memberId) {
		return this.members.removeIf(m -> m.getMemberId().equals(memberId));
	}

	@Override
	public String toString() {
		return "Room{" +
			"id='" + id + '\'' +
			", playgroundId=" + playgroundId +
			", title='" + title + '\'' +
			", masterId=" + masterId +
			", members=" + members +
			", createdAt=" + createdAt +
			", updatedAt=" + updatedAt +
			'}';
	}
}
