package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.dto.AccountDto;
import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.service.impl.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<List<Account>> getAll(){
        List<Account> allAccounts = accountService.findAll();
        return ResponseEntity.ok(allAccounts);
    }
    @GetMapping("/allaccount")
    public ResponseEntity<List<AccountForClientDto>> getAllClientsAccount () {
        List<AccountForClientDto> allClientsAccounts = accountService.findAllClientsAccount();
        return ResponseEntity.ok(allClientsAccounts);
    }
    @GetMapping("/number/{number}")
    public ResponseEntity<Optional<Account>> getAccountByNumber(@PathVariable(name = "number")String accountNumber){
        Optional<Account> account = accountService.getAccountByNumber(accountNumber);
        if (account.isPresent()) return ResponseEntity.ok(account);
        else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }
    @GetMapping("/{accountId}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable(name ="accountId") UUID accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);
        if (Objects.nonNull(account)) return ResponseEntity.ok(account);
        else throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }
}
