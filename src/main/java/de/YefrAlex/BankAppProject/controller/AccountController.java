package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.AccountService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService=accountService;
    }
}
