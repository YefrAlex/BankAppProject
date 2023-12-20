package de.telran_yefralex.BankAppProject.exceptions.exceptionslist;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message, String taxCode) {
        super(message);
    }
}
