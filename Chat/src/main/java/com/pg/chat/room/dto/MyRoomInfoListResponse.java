package com.pg.chat.room.dto;

import java.util.List;

import com.pg.chat.global.common.PageInfo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MyRoomInfoListResponse {
	private final List<RoomInfoResponse> roomInfoList;
	private final PageInfo pageInfo;

	@Override
	public String toString() {
		return "MyRoomInfoListResponse{" +
			"roomInfoList=" + roomInfoList +
			", pageInfo=" + pageInfo +
			'}';
	}
}
