package de.telran_yefralex.BankAppProject.exceptions.exceptionslist;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message, String email) {
        super(message);
    }
}
