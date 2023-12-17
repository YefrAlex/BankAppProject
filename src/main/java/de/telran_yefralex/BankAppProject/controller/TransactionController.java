package de.telran_yefralex.BankAppProject.controller;

import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Transaction;
import de.telran_yefralex.BankAppProject.service.impl.TransactionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService=transactionService;
    }

    @GetMapping("/all/accountNumber/{accountNumber}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsOfAccount(@PathVariable(name = "accountNumber") String accountNumber) {
        return ResponseEntity.ok(transactionService.getAllTransactionsOfAccount(accountNumber));
    }

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/all/taxcode/{taxcode}")
    public ResponseEntity<List<TransactionDto>> findById(@PathVariable(name = "taxcode") String taxCode) {
        return ResponseEntity.ok(transactionService.getAllTransactionsOfTaxCode(taxCode));
    }

    @PostMapping("/addnew")
    public ResponseEntity<UUID> createNewTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction transaction=transactionService.createTransaction(transactionDto);
        return ResponseEntity.ok(transaction.getId());
    }

    @PostMapping("/revers/{id}")
    public ResponseEntity<UUID> reversTransaction(@PathVariable(name = "id") String id) {
        Transaction transaction=transactionService.reversTransaction(id);
        return ResponseEntity.ok(transaction.getId());
    }

}
