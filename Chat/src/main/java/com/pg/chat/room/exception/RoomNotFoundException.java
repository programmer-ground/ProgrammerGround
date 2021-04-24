package com.pg.chat.room.exception;

import com.pg.chat.global.error.ErrorCode;
import com.pg.chat.global.error.exception.BadRequestException;

public class RoomNotFoundException extends BadRequestException {
	public RoomNotFoundException(ErrorCode error) {
		super(error);
	}
}
