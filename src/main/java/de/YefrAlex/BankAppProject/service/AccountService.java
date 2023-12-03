package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.mapper.AccountMapper;
import de.YefrAlex.BankAppProject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
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
}
