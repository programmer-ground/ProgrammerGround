package com.pg.chat.global.error.exception;

import com.pg.chat.global.error.ErrorCode;

public class InvalidParameterException extends BadRequestException {
	public InvalidParameterException(ErrorCode error) {
		super(error);
	}
}

