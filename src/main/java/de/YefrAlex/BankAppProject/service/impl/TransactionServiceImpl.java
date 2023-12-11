package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.TransactionDto;
import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.Transaction;
import de.YefrAlex.BankAppProject.mapper.TransactionMapper;
import de.YefrAlex.BankAppProject.repository.TransactionRepository;
import de.YefrAlex.BankAppProject.service.TransactionService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper) {
        this.transactionRepository=transactionRepository;
        this.transactionMapper=transactionMapper;
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
//        List<Transaction> allTransaction =  transactionRepository.findAll().stream()
//                .filter(an -> (accountNumber.equals(an.getCreditAccountId().getAccountNumber())||accountNumber.equals(an.getDebitAccountId().getAccountNumber()))).collect(Collectors.toList());
//        return transactionMapper.mapToListDto(allTransaction);
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
    public void createTransaction(TransactionDto transactionDto) {

    }
}
