package com.leffycruze.doodle.exception.apirequestexception;

public class ResourceNotFoundException extends ApiRequestException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
