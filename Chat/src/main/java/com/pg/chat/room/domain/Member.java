package com.pg.chat.room.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {
	private Long memberId;
	private Role role;
	private LocalDateTime createdAt;

	@Builder(builderMethodName = "createNewMember")
	Member(Long memberId, Role role) {
		this.memberId = memberId;
		this.role = role;
		this.createdAt = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Member{" +
			"memberId=" + memberId +
			", role=" + role +
			", createdAt=" + createdAt +
			'}';
	}
}
