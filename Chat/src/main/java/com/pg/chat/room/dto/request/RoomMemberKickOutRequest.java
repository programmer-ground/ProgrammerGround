package com.pg.chat.room.dto.request;

public class RoomMemberKickOutRequest {
	private String masterUserId;
	private String kickOutUserId;

	public void setMasterUserId(String masterUserId) {
		this.masterUserId = masterUserId;
	}

	public void setKickOutUserId(String kickOutUserId) {
		this.kickOutUserId = kickOutUserId;
	}

	public String getMasterUserId() {
		return masterUserId;
	}

	public String getKickOutUserId() {
		return kickOutUserId;
	}
}
