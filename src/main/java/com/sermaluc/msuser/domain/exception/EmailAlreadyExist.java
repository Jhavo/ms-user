package com.sermaluc.msuser.domain.exception;

public class EmailAlreadyExist extends RuntimeException {

    public EmailAlreadyExist(String message) {
        super(message);
    }
    
}
