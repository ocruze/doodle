package com.leffycruze.doodle.exception.apirequestexception;

public class UsernameAlreadyTakenException extends ApiRequestException {

    public UsernameAlreadyTakenException(String message) {
        super(message);
    }

    public UsernameAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }
}
