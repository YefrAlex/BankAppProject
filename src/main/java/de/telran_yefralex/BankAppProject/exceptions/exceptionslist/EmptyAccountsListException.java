package de.telran_yefralex.BankAppProject.exceptions.exceptionslist;

public class EmptyAccountsListException extends RuntimeException{

    public EmptyAccountsListException(String message) {
        super(message);
    }
}
