package com.pg.chat.global.error;

public enum ErrorCode {
	ERR_CHAT_ROOM_ALREADY_EXITS(1000, "Playground Chatting Room already exist."),
	ERR_ROOM_NOT_FOUND(1001, "Room Information Not Found."),
	ERR_MEMBER_JOIN_DUPLICATE(1002, "Member Duplicate Error. Member Already Registered."),

	// Common Error start 9000
	ERR_INVALID_REQUEST_PARAMETER(9000, "Client Request Parameter Is Invalid."),
	ERR_UNEXPECTED_SERVER_ERROR(9999, "Unexpected Server Error.");

	private final int code;
	private final String message;

	ErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "ErrorCode{" +
			"message='" + message + '\'' +
			", code=" + code +
			'}';
	}
}
