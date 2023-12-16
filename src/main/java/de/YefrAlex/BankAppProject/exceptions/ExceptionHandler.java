package de.YefrAlex.BankAppProject.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.hibernate.TransactionException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
@RestControllerAdvice
public class ExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return errorMap;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body("Nothing found");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> wrongInput(HttpMessageNotReadableException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body("Wrong request input");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> wrongFkData(DataIntegrityViolationException ex) {
        String errorMessage = "Wrong input. Details: \n" + Objects.requireNonNull(ex.getRootCause()).getLocalizedMessage();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(errorMessage);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> missingData(EmptyResultDataAccessException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body("Cant delete - object missing");
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> missingData(ConstraintViolationException ex) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        return ResponseEntity.status(status).body(ErrorMessage.VALIDATION_FAILED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> missingData(TransactionException ex) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        return ResponseEntity.status(status).body(ErrorMessage.NOT_FOUND_RECORDS);
    }
}
