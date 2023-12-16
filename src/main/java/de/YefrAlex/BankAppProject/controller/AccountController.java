package de.YefrAlex.BankAppProject.controller;


import de.YefrAlex.BankAppProject.dto.AccountDto;
import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.enums.AccountType;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import de.YefrAlex.BankAppProject.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.ServerException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService=accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAll() {
        List<AccountDto> allAccounts=accountService.findAll();
        return ResponseEntity.ok(allAccounts);
    }

    @GetMapping("/allaccount")
    public ResponseEntity<List<AccountForClientDto>> getAllClientsAccount() {
        List<AccountForClientDto> allClientsAccounts=accountService.findAllClientsAccount();
        return ResponseEntity.ok(allClientsAccounts);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<AccountDto> getAccountByNumber(@PathVariable(name = "number") String accountNumber) {
        AccountDto account=accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);

    }


    @PostMapping("/newaccount")
    public ResponseEntity<HttpStatus> createNewAccount(@RequestBody AccountDto accountDto) throws ServerException {
        Account account=accountService.saveAccount(accountDto);
        if (account == null) {
            throw new ServerException("CreatedAccount_Errror");
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("/update/{number}")
    public ResponseEntity<String> updateAccount(
            @PathVariable(name = "number") String accountNumber,
            @RequestParam(name = "mainManagerEmail", required = false) String mainManagerEmail,
            @RequestParam(name = "assistantManagerEmail", required = false) String assistantManagerEmail,
            @RequestParam(name = "type", required = false) AccountType type,
            @RequestParam(name = "currencyCode", required = false) CurrencyCode currencyCode,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked) {
        accountService.updateAccount(accountNumber, mainManagerEmail, assistantManagerEmail, type, currencyCode, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
