package com.pg.chat.global.error.exception;

import com.pg.chat.global.error.ErrorCode;

public class BadRequestException extends RuntimeException {
	private final ErrorCode error;

	public BadRequestException(ErrorCode error) {
		super(error.getMessage());
		this.error = error;
	}

	public BadRequestException(String message, ErrorCode error) {
		super(message);
		this.error = error;
	}

	public ErrorCode getError() {
		return error;
	}

	@Override
	public String toString() {
		return "BadRequest{" +
			"error=" + error +
			'}';
	}
}
