package com.auction.exception;

import com.auction.error.ApiError;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status,
        WebRequest request) {

        ApiError apiError = new ApiError();
        List<String> errors = new ArrayList<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        apiError.setErrors(errors);

        log.error("Exception handler: {}", errors);

        return handleExceptionInternal(exception, apiError, headers, status, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handle(ConstraintViolationException e, WebRequest request) {
        ApiError apiError = new ApiError();
        List<String> errors = new ArrayList<>();

        for (ConstraintViolation error : e.getConstraintViolations()) {
            errors.add(error.getMessage());
        }

        apiError.setErrors(errors);

        log.error("Exception handler: {}", errors);

        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LotCannotBeCreatedOrChangedException.class)
    protected ResponseEntity<ApiError> handle(LotCannotBeCreatedOrChangedException e,
                                              WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setErrors(e.getMessages());
        log.error("Exception handler: {}", e.getMessages());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserCannotBeCreated.class)
    protected ResponseEntity<ApiError> handle(UserCannotBeCreated e,
                                              WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setErrors(e.getMessages());
        log.error("Exception handler: {}", e.getMessages());
        return new ResponseEntity<>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
