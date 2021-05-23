package com.pg.chat.global.error;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pg.chat.global.error.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> requestParameterNotValidExceptionHandler(
		MethodArgumentNotValidException exception
	) {
		BindingResult bindingResult = exception.getBindingResult();

		StringBuilder builder = new StringBuilder();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			builder.append("[");
			builder.append(fieldError.getField());
			builder.append("](은)는 ");
			builder.append(fieldError.getDefaultMessage());
			builder.append(" 입력된 값: [");
			builder.append(fieldError.getRejectedValue());
			builder.append("]");
		}
		String errorMessage = builder.toString();
		log.error("[ApiParameterError] - {}", errorMessage);
		return ResponseEntity.badRequest().body(new ErrorResponse(ErrorCode.ERR_INVALID_REQUEST_PARAMETER));
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ErrorResponse> badRequestExceptionHandler(BadRequestException e) {
		log.info("[{}] - [{}]", e.getClass().getSimpleName(), e.getError());
		return ResponseEntity.badRequest().body(new ErrorResponse(e.getError()));
	}
}
