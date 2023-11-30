package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }
}
