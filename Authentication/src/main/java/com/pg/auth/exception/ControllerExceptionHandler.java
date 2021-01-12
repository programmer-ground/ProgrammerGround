package com.pg.auth.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Error Handler
 * code error 내용
 * 10 : Jwt 토큰 만료
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, Object>> expiredJwtExceptionHandler(ExpiredJwtException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", 10);
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
