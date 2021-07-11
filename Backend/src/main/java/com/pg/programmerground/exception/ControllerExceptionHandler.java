package com.pg.programmerground.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Error Handler
 * code error 내용
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, Object>> expiredJwtExceptionHandler(NoSuchElementException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", 10);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    /**
     * 잘못된 요청 parameter전달
     */
    @ExceptionHandler(WrongRequestException.class)
    public ResponseEntity<Map<String, Object>> WrongRequestExceptionHandler(WrongRequestException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", 11);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileExtractException.class)
    public ResponseEntity<Map<String, Object>> FileExtractExceptionHandler(FileExtractException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", 12);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}