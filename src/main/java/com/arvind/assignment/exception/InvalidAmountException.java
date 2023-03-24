package com.arvind.assignment.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidAmountException extends RuntimeException {
    
    public InvalidAmountException(String message) {
        super(message);
        log.error(message);
    }
    
    public InvalidAmountException(String message, Throwable cause) {
        super(message, cause);
    }
}
