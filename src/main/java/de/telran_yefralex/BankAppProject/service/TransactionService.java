package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.Transaction;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<TransactionDto> getAllTransactions ();
    TransactionDto getTransactionById (UUID id);
    List<TransactionDto> getAllTransactionsOfAccount(String accountNumber);
    List<TransactionDto> getAllTransactionsOfTaxCode(String taxCode);

    Transaction createTransaction(TransactionDto transactionDto, CurrencyCode currencyCode);
    Transaction reversTransaction(String id);
    Boolean checkCreditAccountInCurrency (Account creditAccount, BigDecimal amountInCurrency);
    BigDecimal convertToEuro(BigDecimal amount, CurrencyCode currencyCode);
    BigDecimal convertFromEuro(BigDecimal amount, CurrencyCode currencyCode);
}
