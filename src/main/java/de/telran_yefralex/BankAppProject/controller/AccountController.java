package de.telran_yefralex.BankAppProject.controller;


import de.telran_yefralex.BankAppProject.dto.AccountDto;
import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.List;


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


    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createNewAccount(@RequestBody AccountDto accountDto) throws ServerException {
        Account account=accountService.saveAccount(accountDto);
        if (account == null) {
            throw new ServerException("CreatedAccount_Error");
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
