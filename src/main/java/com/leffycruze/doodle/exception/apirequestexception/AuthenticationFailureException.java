package com.leffycruze.doodle.exception.apirequestexception;

public class AuthenticationFailureException extends ApiRequestException {
    public AuthenticationFailureException(String message) {
        super(message);
    }

    public AuthenticationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
