package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.AccountDto;
import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

      List<AccountDto> findAll ();

      List<AccountForClientDto> findAllClientsAccount();

    AccountDto getAccountByNumber(String accountNumber);

//    Optional<Account> findByIdForTransaction(UUID id);

    Account saveAccount(AccountDto accountDto);

    void updateAccount(String accountNumber, String mainManagerEmail, String assistantManagerEmail, AccountType type, CurrencyCode currencyCode, Boolean isBlocked);
}
