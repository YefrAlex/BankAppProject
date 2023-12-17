package de.telran_yefralex.BankAppProject.exceptions;


import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
//        Map<String, String> errorMap = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
//        return errorMap;
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyAccountsListException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyAccountsListException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAccountNotFoundException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AgreementNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleAgreementNotFoundException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyAgreementsListException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyAgreementListException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleClientNotFoundException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyClientsListException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyClientsListException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEmployeeNotFoundException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyEmployeeListException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyEmployeeListException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyProductsListException.class)
    public ResponseEntity<ErrorResponseDto> handleEmptyProductsListException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmptyTransactionsListException.class)
    public ResponseEntity<ErrorResponseDto> handleTransactionsProductsListException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleTransactionNotFoundException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BalanceException.class)
    public ResponseEntity<ErrorResponseDto> handleBalanceException(Exception e){
        ErrorResponseDto errorResponseDto= new ErrorResponseDto();
        errorResponseDto.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
        errorResponseDto.setMessage(e.getMessage());
        errorResponseDto.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponseDto);
    }

}
