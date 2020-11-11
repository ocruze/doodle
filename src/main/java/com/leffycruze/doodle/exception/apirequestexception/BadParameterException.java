package com.leffycruze.doodle.exception.apirequestexception;

public class BadParameterException extends ApiRequestException {

    public BadParameterException(String message) {
        super(message);
    }

    public BadParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
