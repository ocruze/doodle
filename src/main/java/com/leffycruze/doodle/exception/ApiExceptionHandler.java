package com.leffycruze.doodle.exception;

import com.leffycruze.doodle.exception.apirequestexception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    private ResponseEntity<Object> handleException(ApiRequestException e, HttpStatus httpStatus) {
        return new ResponseEntity<>(
                new ErrorResponse(e.getMessage(), httpStatus, httpStatus.value(), ZonedDateTime.now()), httpStatus);
    }

    @ExceptionHandler(value = { UsernameAlreadyTakenException.class, MissingParametersException.class,
            BadParameterException.class })
    public ResponseEntity<Object> handleExceptionBadRequest(ApiRequestException e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { UserNotFoundException.class })
    public ResponseEntity<Object> handleExceptionNotFound(ApiRequestException e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { AuthenticationFailureException.class })
    public ResponseEntity<Object> handleExceptionUnauthorized(ApiRequestException e) {
        return handleException(e, HttpStatus.FORBIDDEN);
    }
}
