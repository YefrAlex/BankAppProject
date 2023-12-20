package de.telran_yefralex.BankAppProject.exceptions.exceptionslist;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
