package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.AccountDto;
import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.dto.TransactionDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.Transaction;
import de.YefrAlex.BankAppProject.entity.enums.AccountType;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

      List<AccountDto> findAll ();

      List<AccountForClientDto> findAllClientsAccount();

    AccountDto getAccountByNumber(String accountNumber);

    Optional<Account> findByIdForTransaction(UUID id);

    Account saveAccount(AccountDto accountDto);

    void updateAccount(String accountNumber, String mainManagerEmail, String assistantManagerEmail, AccountType type, CurrencyCode currencyCode, Boolean isBlocked);
}
