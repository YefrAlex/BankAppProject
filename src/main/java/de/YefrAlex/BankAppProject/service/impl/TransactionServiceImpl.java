package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.TransactionDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.Transaction;
import de.YefrAlex.BankAppProject.mapper.AccountMapper;
import de.YefrAlex.BankAppProject.mapper.TransactionMapper;
import de.YefrAlex.BankAppProject.repository.TransactionRepository;
import de.YefrAlex.BankAppProject.service.AccountService;
import de.YefrAlex.BankAppProject.service.TransactionService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountService accountService, AccountMapper accountMapper) {
        this.transactionRepository=transactionRepository;
        this.transactionMapper=transactionMapper;
        this.accountService=accountService;
        this.accountMapper=accountMapper;
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





    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        Account debitAccountIt = null; //accountService.getAccountByNumber1(transactionDto.getDebitAccountNumber());
        Account creditAccountIt = null; //accountService.getAccountByNumber1(transactionDto.getCreditAccountNumber());
//        Optional <Account> debitAccountItOptional = accountService.getAccountByNumber(transactionDto.getDebitAccountNumber());
//        //checkDebitAccountOwner(debitAccount);
//        Optional <Account> creditAccountItOptional = accountService.getAccountByNumber(transactionDto.getCreditAccountNumber());
//        Transaction transaction=null;
//        if (debitAccountItOptional.isPresent()&&creditAccountItOptional.isPresent()){
//             transaction = saveTransactionFromDto(transactionDto, debitAccountItOptional.get(), creditAccountItOptional.get());
//        }
        Transaction transaction = saveTransactionFromDto(transactionDto, debitAccountIt, creditAccountIt);
          return transaction;
    }

    private Transaction saveTransactionFromDto(TransactionDto transactionDto, Account debitAccountIt, Account creditAccountIt) {
        Transaction transaction = new Transaction();
        //transaction.setId(null);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setDebitAccountId(debitAccountIt);
        transaction.setCreditAccountId(creditAccountIt);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setType(transactionDto.getType());
        return transaction;
    }
}
