package de.telran_yefralex.BankAppProject.exceptions.exceptionslist;

public class EmptyTransactionsListException extends RuntimeException {
    public EmptyTransactionsListException(String message) {
        super(message);
    }
}
