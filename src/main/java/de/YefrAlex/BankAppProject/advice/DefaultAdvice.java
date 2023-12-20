package de.YefrAlex.BankAppProject.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {
    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ResponseDto> handleException(ResponseException e) {
        ResponseDto responseDto = new ResponseDto(e.getMessage(), e.getLocalizedMessage());

        return new ResponseEntity<>(responseDto, HttpStatus.resolve(400));
    }
}
