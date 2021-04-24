package com.pg.chat.room.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.pg.chat.room.domain.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewChatRoomCreateResponse {
	private String roomId;
	private long playGroundId;
	private String title;
	private List<Member> members = new ArrayList<>();
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	@Override
	public String toString() {
		return "NewChatRoomCreateResponse{" +
			"roomId='" + roomId + '\'' +
			", playGroundId=" + playGroundId +
			", title='" + title + '\'' +
			", members=" + members +
			", createdDate=" + createdDate +
			", updatedDate=" + updatedDate +
			'}';
	}
}
