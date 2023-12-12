package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.dto.ProductDto;
import de.YefrAlex.BankAppProject.dto.TransactionDto;
import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.Transaction;
import de.YefrAlex.BankAppProject.service.impl.TransactionServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService=transactionService;
    }

    @GetMapping("/all/{accountNumber}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsOfAccount(@PathVariable(name = "accountNumber") String accountNumber) {
        return ResponseEntity.ok(transactionService.getAllTransactionsOfAccount(accountNumber));
    }

    @GetMapping("/allall")
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
        return ResponseEntity.created(URI.create("/" + transaction.getId())).body(transaction.getId());
    }
}
