package com.habeeb.ecommerce.exceptions;

public class AlreadyExistException extends RuntimeException {

    // Custom exception handler
    public AlreadyExistException(String message){
        super(message);
    }
}
