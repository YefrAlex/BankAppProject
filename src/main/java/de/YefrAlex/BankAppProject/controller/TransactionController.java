package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.impl.TransactionServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService=transactionService;
    }
}
