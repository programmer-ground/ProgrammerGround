package com.pg.chat.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pg.chat.global.error.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestExceptionHandler(BadRequestException e) {
		log.info("[{}] - [{}]", e.getClass().getSimpleName(), e.getError());
		return ResponseEntity.badRequest().body(new ErrorResponse(e.getError()));
	}
}
