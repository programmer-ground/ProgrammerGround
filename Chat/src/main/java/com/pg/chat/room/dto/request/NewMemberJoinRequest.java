package com.pg.chat.room.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.pg.chat.global.validator.EnumValidation;
import com.pg.chat.room.domain.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewMemberJoinRequest {
	@Positive
	@NotNull
	private Long memberId;
	@NotNull
	@EnumValidation(enumClass = Role.class)
	private String memberRole;

	@Override
	public String toString() {
		return "NewMemberJoinRequest{" +
			"memberId=" + memberId +
			", memberRole=" + memberRole +
			'}';
	}
}
