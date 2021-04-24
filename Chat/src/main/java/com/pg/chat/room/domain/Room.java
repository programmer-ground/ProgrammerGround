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
@Document(collection = "rooms")
public class Room {
	@Id
	private String id;

	@Indexed
	private Long playGroundId;

	private String title;

	private Long masterId;

	private Set<Member> members = new HashSet<>();

	private LocalDateTime createdDate;

	private LocalDateTime updatedDate;

	@Builder(builderMethodName = "createNewRoom")
	Room(Long playGroundId, String title, Long masterId) {
		this.playGroundId = playGroundId;
		this.title = title;
		this.masterId = masterId;
		this.createdDate = LocalDateTime.now();
		this.updatedDate = LocalDateTime.now();
	}

	public void joinNewMember(Member member) {
		this.members.add(member);
	}

	public boolean memberExist(Long memberId, Role role) {
		return this.members.contains(Member.of(memberId, role));
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
			", playGroundId=" + playGroundId +
			", title='" + title + '\'' +
			", masterId=" + masterId +
			", members=" + members +
			", createdDate=" + createdDate +
			", updatedDate=" + updatedDate +
			'}';
	}
}
