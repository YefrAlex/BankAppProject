package de.telran_yefralex.BankAppProject.controller;

import de.telran_yefralex.BankAppProject.controller.interfaces.TransactionControllerInterface;
import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Transaction;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.service.impl.TransactionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/transaction")
public class TransactionController implements TransactionControllerInterface {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService=transactionService;
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/all/accountNumber/{accountNumber}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsOfAccount(@PathVariable(name = "accountNumber") String accountNumber) {
        return ResponseEntity.ok(transactionService.getAllTransactionsOfAccount(accountNumber));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(Principal principal) {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/all/taxcode/{taxcode}")
    public ResponseEntity<List<TransactionDto>> findByTaxCode(@PathVariable(name = "taxcode") String taxCode) {
        return ResponseEntity.ok(transactionService.getAllTransactionsOfTaxCode(taxCode));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @PostMapping("/addnew")
    public ResponseEntity<UUID> createNewTransaction(@RequestBody TransactionDto transactionDto,
                                                     @RequestParam(name = "currencyCode", required = true) CurrencyCode currencyCode) {
        Transaction transaction=transactionService.createTransaction(transactionDto, currencyCode);
        return ResponseEntity.ok(transaction.getId());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/revers/{id}")
    public ResponseEntity<UUID> reversTransaction(@PathVariable(name = "id") String id) {
        Transaction transaction=transactionService.reversTransaction(id);
        return ResponseEntity.ok(transaction.getId());
    }

}
