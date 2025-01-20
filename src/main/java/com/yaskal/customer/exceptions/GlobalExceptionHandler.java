package com.yaskal.customer.exceptions;

import com.yaskal.customer.api.model.BaseErrorResponse;
import com.yaskal.customer.exceptions.model.CustomerNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    public static final String CAUGHT_ERROR = "[GlobalExceptionHandler] Caught Error: ";
    public static final String VALIDATION_FAILED_MESSAGE = "Validation failed for one or more fields.";
    public static final String DELIMITER = ", ";

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<BaseErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        log.error(CAUGHT_ERROR, ex);
        BaseErrorResponse errorResponse = new BaseErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<BaseErrorResponse> handleRuntimeException(Throwable ex) {
        log.error(CAUGHT_ERROR, ex);
        BaseErrorResponse errorResponse = new BaseErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error(CAUGHT_ERROR, ex);

        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(DELIMITER));

        BaseErrorResponse errorResponse = new BaseErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(CAUGHT_ERROR, ex);

        List<String> errorMessages = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());


        BaseErrorResponse errorResponse = new BaseErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                VALIDATION_FAILED_MESSAGE,
                errorMessages
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
