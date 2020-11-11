package com.leffycruze.doodle.exception.apirequestexception;

public class UserNotFoundException extends ApiRequestException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
