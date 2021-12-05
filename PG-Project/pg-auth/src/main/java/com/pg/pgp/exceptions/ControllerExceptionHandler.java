package com.pg.pgp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Error Handler
 * code error 내용
 * 20 : Login Code 에러
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<Map<String, Object>> jwtExceptionHandler(InvalidCodeException e) {
        return new ResponseEntity<>(makeErrorCode(20, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> missingRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        return new ResponseEntity<>(makeErrorCode(21, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> noSuchElementExceptionHandler(NoSuchElementException e) {
        return new ResponseEntity<>(makeErrorCode(22, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JwtNotFoundException.class)
    public ResponseEntity<Map<String, Object>> jwtNotFoundExceptionHandler(JwtNotFoundException e) {
        return new ResponseEntity<>(makeErrorCode(22, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, Object> makeErrorCode(int code, String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        return map;
    }
}
