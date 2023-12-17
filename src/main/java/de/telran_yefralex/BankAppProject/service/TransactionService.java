package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<TransactionDto> getAllTransactions ();
    TransactionDto getTransactionById (UUID id);
    List<TransactionDto> getAllTransactionsOfAccount(String accountNumber);
    List<TransactionDto> getAllTransactionsOfTaxCode(String taxCode);

    Transaction createTransaction(TransactionDto transactionDto);

    Transaction reversTransaction(String id);
}
