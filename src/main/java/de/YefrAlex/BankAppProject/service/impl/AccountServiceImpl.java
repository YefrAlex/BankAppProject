package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.mapper.AccountMapper;
import de.YefrAlex.BankAppProject.repository.AccountRepository;
import de.YefrAlex.BankAppProject.service.AccountService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository=accountRepository;
        this.accountMapper=accountMapper;
    }

    public List<Account> findAll () {
        List<Account> allAccounts =accountRepository.findAll();
        return allAccounts;
    }

    public List<AccountForClientDto> findAllClientsAccount(){
        List<Account> clientsAccount = accountRepository.findAll();
        return clientsAccount.stream()
                .map(accountMapper::toAccountForClientDto)
                .collect(Collectors.toList());

    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.getByNumber(accountNumber);
    }
}
