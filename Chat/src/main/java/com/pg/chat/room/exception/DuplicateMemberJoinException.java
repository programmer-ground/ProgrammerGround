package com.pg.chat.room.exception;

import com.pg.chat.global.error.ErrorCode;
import com.pg.chat.global.error.exception.BadRequestException;

public class DuplicateMemberJoinException extends BadRequestException {
	public DuplicateMemberJoinException(ErrorCode error) {
		super(error);
	}
}
