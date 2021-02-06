package com.pg.chat.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
	private String nickname;
	private String profile;

	@Builder
	public User(String nickname, String profile) {
		this.nickname = nickname;
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "User{" +
			"nickname='" + nickname + '\'' +
			", profile='" + profile + '\'' +
			'}';
	}
}
