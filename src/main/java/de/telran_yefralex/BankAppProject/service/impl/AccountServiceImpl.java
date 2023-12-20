package de.telran_yefralex.BankAppProject.service.impl;


import de.telran_yefralex.BankAppProject.dto.AccountDto;
import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.AccountNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyAccountsListException;
import de.telran_yefralex.BankAppProject.mapper.AccountMapper;
import de.telran_yefralex.BankAppProject.repository.AccountRepository;
import de.telran_yefralex.BankAppProject.repository.ClientRepository;
import de.telran_yefralex.BankAppProject.repository.EmployeeRepository;
import de.telran_yefralex.BankAppProject.service.AccountService;
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
        if (allAccounts.isEmpty()) {
            throw new EmptyAccountsListException(ErrorMessage.EMPTY_ACCOUNTS_LIST);
        }
        return allAccounts.stream().map(accountMapper::toAccountDto)
                .collect(Collectors.toList());
    }

    public List<AccountForClientDto> findAllClientsAccount() {
        List<Account> clientsAccounts = accountRepository.findAll();
        if (clientsAccounts.isEmpty()) {
            throw new EmptyAccountsListException(ErrorMessage.EMPTY_ACCOUNTS_LIST);
        }
        return clientsAccounts.stream()
                .map(accountMapper::toAccountForClientDto)
                .collect(Collectors.toList());

    }

    @Override
    public AccountDto getAccountByNumber(String accountNumber) {
        Account account = accountRepository.getByNumber(accountNumber).orElseThrow(() -> new AccountNotFoundException(String.format(ErrorMessage.ACCOUNT_NOT_FOUND, accountNumber)));
        return accountMapper.toAccountDto(account);
    }

//    @Override
//    public Optional<Account> findByIdForTransaction(UUID id) {
//        if (accountRepository.findById(id).isEmpty()){
//            throw new AccountNotFoundException(String.format(ErrorMessage.ACCOUNT_NOT_FOUND, id));
//        }
//        return accountRepository.findById(id);
//    }

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
        Account account=accountRepository.getByNumber(accountNumber).orElseThrow(
                () -> new AccountNotFoundException(String.format(ErrorMessage.ACCOUNT_NOT_FOUND, accountNumber)));
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







