package com.sermaluc.msuser.infrastructure.adapters.output.customizedException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.sermaluc.msuser.domain.exception.EmailAlreadyExist;
import com.sermaluc.msuser.domain.exception.UserNotFound;
import com.sermaluc.msuser.infrastructure.adapters.output.customizedException.data.response.ExceptionResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@RestController
@Slf4j
public class CustomizedExceptionAdapter extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		log.info("[CustomizedExceptionAdapter:handleAllExceptions]: message: {}", ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<Object> handleUserNotFoundException(UserNotFound ex, WebRequest request) {
		log.info("[CustomizedExceptionAdapter:handleUserNotFoundException]: message: {}", ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExist.class)
    public final ResponseEntity<Object> handleEmailAlreadyExistException(EmailAlreadyExist ex, WebRequest request) {
		log.info("[CustomizedExceptionAdapter:handleEmailAlreadyExistException]: message: {}", ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
            errors.add(error.getDefaultMessage());
        });

        ExceptionResponse exceptionResponse = new ExceptionResponse(errors.get(0));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
