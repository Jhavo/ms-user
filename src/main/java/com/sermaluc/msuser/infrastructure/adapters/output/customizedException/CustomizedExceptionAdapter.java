package com.sermaluc.msuser.infrastructure.adapters.output.customizedException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sermaluc.msuser.domain.exception.UserNotFound;
import com.sermaluc.msuser.infrastructure.adapters.output.customizedException.data.response.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@ControllerAdvice
@Slf4j
public class CustomizedExceptionAdapter extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		log.info("[CustomizedExceptionAdapter:handleAllExceptions]: ex: {}, request: {}", ex, request);
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), Arrays.asList(request.getDescription(false)));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFound ex, WebRequest request) {
		log.info("[CustomizedExceptionAdapter:handleUserNotFoundException]: ex: {}, request: {}", ex, request);
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), Arrays.asList(request.getDescription(false)));
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("[CustomizedExceptionAdapter:handleMethodArgumentNotValid]: ex: {}, headers: {}, status: {}, request: {}", ex, headers, status, request);
        List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), "Validation Failed", errors);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
