package com.habeeb.ecommerce.exceptions;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message){
        super(message);
    }
}
