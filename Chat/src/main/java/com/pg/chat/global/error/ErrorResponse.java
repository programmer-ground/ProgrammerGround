package com.pg.chat.global.error;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private final Map data;
	private final String service = "pg-chat";
	private final String message;
	private final int code;

	public ErrorResponse(ErrorCode error) {
		this.message = error.getMessage();
		this.code = error.getCode();
		this.data = new HashMap();
	}

	@Override
	public String toString() {
		return "ErrorResponse{" +
			"message='" + message + '\'' +
			", code=" + code +
			", data=" + data +
			", service='" + service + '\'' +
			'}';
	}
}
