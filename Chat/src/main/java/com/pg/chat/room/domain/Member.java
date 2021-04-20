package com.pg.chat.room.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = {"memberId", "role"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
	private Long memberId;
	private Role role;
	private LocalDateTime createdDate;

	@Builder(builderMethodName = "createNewMember")
	Member(Long memberId, Role role) {
		this.memberId = memberId;
		this.role = role;
		this.createdDate = LocalDateTime.now();
	}

	public static Member of(long memberId, Role role) {
		return Member.createNewMember()
			.memberId(memberId)
			.role(role)
			.build();
	}

	public void roleChange(Role changeRole) {
		this.role = changeRole;
	}

	@Override
	public String toString() {
		return "Member{" +
			"memberId=" + memberId +
			", role=" + role +
			", createdDate=" + createdDate +
			'}';
	}
}
