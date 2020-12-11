package com.leffycruze.doodle.exception.apirequestexception;

public class BadRequestException extends ApiRequestException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
