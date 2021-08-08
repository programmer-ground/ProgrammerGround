package com.pg.programmerground.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    public ResponseEntity<Map<String, Object>> wrongRequestExceptionHandler(WrongRequestException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", 11);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileExtractException.class)
    public ResponseEntity<Map<String, Object>> fileExtractExceptionHandler(FileExtractException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("code", 12);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    /**
     * @Validated 검증 에러
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, Object>> bindExceptionHandler(Exception e) {
        Map<String, Object> map = new HashMap<>();
        BindingResult bindingResult;
        //excpetion 마다
        if(e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else {
            bindingResult = ((BindException) e).getBindingResult();
        }
        //msg 생성
        String errorMsg = bindingResult.getFieldErrors().stream()
                .map(fe -> fe.getField() + " " + fe.getRejectedValue() + " " + fe.getDefaultMessage())
                .collect(Collectors.joining(", "));

        map.put("msg", errorMsg);
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Map<String, Object>> fileNotFoundExceptionHandler(FileNotFoundException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "파일이 존재하지 않습니다.");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
    }
}