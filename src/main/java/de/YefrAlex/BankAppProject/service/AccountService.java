package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository=accountRepository;
    }

    public List<Account> findAll () {
        List<Account> allAccounts =accountRepository.findAll();
        return allAccounts;
    }
}
