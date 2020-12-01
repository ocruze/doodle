package com.leffycruze.doodle.exception.apirequestexception;

public class ResourceForbiddenException extends ApiRequestException {

    public ResourceForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceForbiddenException(String message) {
        super(message);
    }

}
