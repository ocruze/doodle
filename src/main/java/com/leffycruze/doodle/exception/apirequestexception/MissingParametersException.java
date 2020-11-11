package com.leffycruze.doodle.exception.apirequestexception;

public class MissingParametersException extends ApiRequestException {

    public MissingParametersException(String message) {
        super(message);
    }

    public MissingParametersException(String message, Throwable cause) {
        super(message, cause);
    }
}
