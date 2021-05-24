package com.pg.chat.room.exception;

import com.pg.chat.global.error.ErrorCode;
import com.pg.chat.global.error.exception.BadRequestException;

public class RoomManagementPermissionDeniedException extends BadRequestException {
	public RoomManagementPermissionDeniedException(ErrorCode errorCode) {
		super(errorCode);
	}
}
