package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.service.impl.AccountServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService=accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAll(){
        List<Account> allAccounts = accountService.findAll();
        return ResponseEntity.ok(allAccounts);
    }
    @GetMapping("/allaccount")
    public ResponseEntity<List<AccountForClientDto>> getAllClientsAccount () {
        List<AccountForClientDto> allClientsAccounts = accountService.findAllClientsAccount();
        return ResponseEntity.ok(allClientsAccounts);
    }
    @GetMapping("/number")
    Account getAccountByNumber(String accountNumber){

                return accountService.getAccountByNumber(accountNumber);
    }
}
