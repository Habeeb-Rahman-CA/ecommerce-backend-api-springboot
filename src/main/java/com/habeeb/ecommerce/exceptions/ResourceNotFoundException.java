package com.habeeb.ecommerce.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    // Custom exception handler
    public ResourceNotFoundException(String message){
        super(message);
    }
}
