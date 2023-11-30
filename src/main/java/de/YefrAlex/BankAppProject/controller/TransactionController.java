package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.TransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService=transactionService;
    }
}
