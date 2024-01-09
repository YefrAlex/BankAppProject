package de.telran_yefralex.BankAppProject.controller;

import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Transaction;
import de.telran_yefralex.BankAppProject.service.impl.TransactionServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    public TransactionController(TransactionServiceImpl transactionService) {
        this.transactionService=transactionService;
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/all/accountNumber/{accountNumber}")
    public ResponseEntity<List<TransactionDto>> getAllTransactionsOfAccount(@PathVariable(name = "accountNumber") String accountNumber,
       Principal principal, HttpServletRequest request) {
        log.info("all transactions for accountNumber = " + accountNumber + " watched by user/manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(transactionService.getAllTransactionsOfAccount(accountNumber));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDto>> getAllTransactions(Principal principal, HttpServletRequest request) {
        log.info("all transactions  watched by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/{id}")
    public ResponseEntity<TransactionDto> findById(@PathVariable(name = "id") UUID id, Principal principal, HttpServletRequest request) {
        log.info("transaction with = " + id + " watched by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @GetMapping("/all/taxcode/{taxcode}")
    public ResponseEntity<List<TransactionDto>> findById(@PathVariable(name = "taxcode") String taxCode, Principal principal, HttpServletRequest request) {
        log.info("all transactions for taxCode = " + taxCode + " watched by user/manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(transactionService.getAllTransactionsOfTaxCode(taxCode));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_USER')")
    @PostMapping("/addnew")
    public ResponseEntity<UUID> createNewTransaction(@RequestBody TransactionDto transactionDto, Principal principal, HttpServletRequest request) {
        Transaction transaction=transactionService.createTransaction(transactionDto);
        log.info("transaction with id " + transaction.getId() + " created by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(transaction.getId());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/revers/{id}")
    public ResponseEntity<UUID> reversTransaction(@PathVariable(name = "id") String id, Principal principal, HttpServletRequest request ) {
        Transaction transaction=transactionService.reversTransaction(id);
        log.info("reverstransaction with = " + transaction.getId() + " for wrong transaction with id " + id +
                " created by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(transaction.getId());
    }

}
