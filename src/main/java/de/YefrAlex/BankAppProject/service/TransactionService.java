package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.TransactionDto;
import de.YefrAlex.BankAppProject.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<TransactionDto> getAllTransactions ();
    TransactionDto getTransactionById (UUID id);
    List<TransactionDto> getAllTransactionsOfAccount(String accountNumber);
    List<TransactionDto> getAllTransactionsOfTaxCode(String taxCode);

    void createTransaction(TransactionDto transactionDto);


}
