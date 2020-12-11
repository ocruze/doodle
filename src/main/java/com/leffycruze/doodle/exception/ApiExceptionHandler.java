package com.leffycruze.doodle.exception;

import com.leffycruze.doodle.exception.apirequestexception.ApiRequestException;
import com.leffycruze.doodle.exception.apirequestexception.BadRequestException;
import com.leffycruze.doodle.exception.apirequestexception.ForbiddenException;
import com.leffycruze.doodle.exception.apirequestexception.NotFoundException;
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

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleExceptionBadRequest(ApiRequestException e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleExceptionNotFound(ApiRequestException e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<Object> handleExceptionUnauthorized(ApiRequestException e) {
        return handleException(e, HttpStatus.FORBIDDEN);
    }
}
