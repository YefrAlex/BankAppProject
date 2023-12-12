package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.TransactionDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.Transaction;
import de.YefrAlex.BankAppProject.mapper.TransactionMapper;
import de.YefrAlex.BankAppProject.repository.TransactionRepository;
import de.YefrAlex.BankAppProject.service.AccountService;
import de.YefrAlex.BankAppProject.service.TransactionService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountService accountService) {
        this.transactionRepository=transactionRepository;
        this.transactionMapper=transactionMapper;
        this.accountService=accountService;
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        return transactionMapper.mapToListDto(transactions) ;
    }

    @Override
    public TransactionDto getTransactionById(UUID id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new ExpressionException("Transaction not found with id " + id));
        return transactionMapper.toTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getAllTransactionsOfAccount(String accountNumber) {
        return transactionMapper.mapToListDto(transactionRepository
               .getAllTransactionsWithAccountNumber(accountNumber));
    }
    @Override
    public List<TransactionDto> getAllTransactionsOfTaxCode(String taxCode) {
        return transactionMapper.mapToListDto(transactionRepository
                .getAllTransactionsWithTaxCode(taxCode));
    }
//    public List<TransactionDto> getAllDebitTransactionsOfAccount(String accountNumber) {
//        return transactionMapper.mapToListDto(transactionRepository
//                .getAllDebitTransactionsWithAccountNumber(accountNumber));
//    }
//    public List<TransactionDto> getAllCreditTransactionsOfAccount(String accountNumber) {
//        return transactionMapper.mapToListDto(transactionRepository
//                .getAllCreditTransactionsWithAccountNumber(accountNumber));
//    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {

        Account debitAccountIt = accountService.getAccountByNumber(transactionDto.getDebitAccountNumber());
        //checkDebitAccountOwner(debitAccount);
        Account creditAccountIt = accountService.getAccountByNumber(transactionDto.getCreditAccountNumber());
        Transaction transaction = saveTransactionFromDto(transactionDto, debitAccountIt, creditAccountIt);;
           return transaction;
    }

    private Transaction saveTransactionFromDto(TransactionDto transactionDto, Account debitAccountIt, Account creditAccountIt) {
        Transaction transaction = new Transaction();
        transaction.setId(null);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setDebitAccountId(debitAccountIt);
        transaction.setCreditAccountId(creditAccountIt);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setType(transactionDto.getType());
        return transaction;
    }
}
