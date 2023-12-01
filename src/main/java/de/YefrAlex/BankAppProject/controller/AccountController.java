package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService=accountService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAll(){
        List<Account> allAccounts = accountService.findAll();
        return ResponseEntity.ok(allAccounts);
    }
}
