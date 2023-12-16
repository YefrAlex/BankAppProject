package de.YefrAlex.BankAppProject.service.impl;


import de.YefrAlex.BankAppProject.dto.AccountDto;
import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.enums.AccountType;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import de.YefrAlex.BankAppProject.mapper.AccountMapper;
import de.YefrAlex.BankAppProject.repository.AccountRepository;
import de.YefrAlex.BankAppProject.repository.ClientRepository;
import de.YefrAlex.BankAppProject.repository.EmployeeRepository;
import de.YefrAlex.BankAppProject.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper, ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.accountRepository=accountRepository;
        this.accountMapper=accountMapper;
        this.clientRepository=clientRepository;
        this.employeeRepository=employeeRepository;
    }

    public List<AccountDto> findAll() {
        List<Account> allAccounts=accountRepository.findAll();
        return allAccounts.stream().map(accountMapper::toAccountDto)
                .collect(Collectors.toList());
    }

    public List<AccountForClientDto> findAllClientsAccount() {
        List<Account> clientsAccount=accountRepository.findAll();
        return clientsAccount.stream()
                .map(accountMapper::toAccountForClientDto)
                .collect(Collectors.toList());

    }

    @Override
    public AccountDto getAccountByNumber(String accountNumber) {
        Account account = accountRepository.getByNumber(accountNumber).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return accountMapper.toAccountDto(account);

    }





    @Override
    public Optional<Account> findByIdForTransaction(UUID id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account saveAccount(AccountDto accountDto) {
        Account account=new Account();
        account.setClientId(clientRepository.findClientByTaxCode(accountDto.getTaxCode()));
        account.setMainManagerId(employeeRepository.findEmployeeByEmail(accountDto.getMainManager()));
        if (accountDto.getAssistantManager() == null) {
            account.setAssistantManagerId(null);
        } else account.setAssistantManagerId(employeeRepository.findEmployeeByEmail(accountDto.getAssistantManager()));
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setType(accountDto.getType());
        account.setBalance(accountDto.getBalance());
        account.setCurrencyCode(accountDto.getCurrencyCode());
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setBlocked(false);
        accountRepository.save(account);
        return account;
    }

    @Override
    public void updateAccount(String accountNumber, String mainManagerEmail, String assistantManagerEmail, AccountType type, CurrencyCode currencyCode, Boolean isBlocked) {
        Account account=accountRepository.getByNumber(accountNumber).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        if (mainManagerEmail != null) {
            account.setMainManagerId(employeeRepository.findEmployeeByEmail(mainManagerEmail));
        }
        if (assistantManagerEmail != null) {
            account.setAssistantManagerId(employeeRepository.findEmployeeByEmail(assistantManagerEmail));
        }
        if (type != null) {
            account.setType(type);
        }
        if (currencyCode != null) {
            account.setCurrencyCode(currencyCode);
        }
        if (isBlocked != null) {
            account.setBlocked(isBlocked);
        }
        accountRepository.save(account);
    }
}







