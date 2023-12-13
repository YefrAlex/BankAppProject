package de.YefrAlex.BankAppProject.exceptions;

import java.util.UUID;

public class AccountNotFoundException extends RuntimeException {

    AccountNotFoundException(UUID id) {
        super("Could not find account " + id);
    }
    AccountNotFoundException(String accountNumber) {
        super("Could not find account " + accountNumber);
    }
}
