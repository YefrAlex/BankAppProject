package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.AccountDto;
import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountService {

      List<Account> findAll ();

      List<AccountForClientDto> findAllClientsAccount();

    Optional<Account> getAccountByNumber(String accountNumber);

    Optional<Account> getAccountById(UUID accountId);
    Account getAccountByNumber1(String accountNumber);
}
