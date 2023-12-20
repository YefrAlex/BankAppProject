package de.telran_yefralex.BankAppProject.exceptions.exceptionslist;

public class EmailIsUsedException extends RuntimeException {
    public EmailIsUsedException(String message, String email) {
        super(message);
    }
}
