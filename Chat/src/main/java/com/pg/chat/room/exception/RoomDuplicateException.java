package com.pg.chat.room.exception;

import com.pg.chat.global.error.ErrorCode;
import com.pg.chat.global.error.exception.BadRequestException;

public class RoomDuplicateException extends BadRequestException {
	public RoomDuplicateException(ErrorCode error) {
		super(error);
	}
}
