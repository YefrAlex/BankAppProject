package de.telran_yefralex.BankAppProject.controller;


import de.telran_yefralex.BankAppProject.dto.AccountDto;
import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService=accountService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> getAll() {
        List<AccountDto> allAccounts=accountService.findAll();
        return ResponseEntity.ok(allAccounts);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/allmanagers")
    public ResponseEntity<List<AccountDto>> getManagerAll(Principal principal) {
        String managerEmail = principal.getName();
        List<AccountDto> allAccounts=accountService.findManagerAll(managerEmail);
        return ResponseEntity.ok(allAccounts);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/allaccount")
    public ResponseEntity<List<AccountForClientDto>> getAllClientsAccount(Principal principal) {
        String userEmail = principal.getName();
        List<AccountForClientDto> allClientsAccounts=accountService.findAllClientsAccount(userEmail);
        return ResponseEntity.ok(allClientsAccounts);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/number/{number}")
    public ResponseEntity<AccountDto> getAccountByNumber(@PathVariable(name = "number") String accountNumber) {
        AccountDto account=accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/new")
    public ResponseEntity<HttpStatus> createNewAccount(@RequestBody AccountDto accountDto, Principal principal) throws ServerException {
        accountDto.setMainManager(principal.getName());
        Account account=accountService.saveAccount(accountDto);
        if (account == null) {
            throw new ServerException("CreatedAccount_Error");
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
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
