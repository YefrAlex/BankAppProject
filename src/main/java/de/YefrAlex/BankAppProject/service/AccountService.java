package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;


import java.util.List;

public interface AccountService {

      List<Account> findAll ();

      List<AccountForClientDto> findAllClientsAccount();
}
