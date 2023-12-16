package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.TransactionDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.Transaction;
import de.YefrAlex.BankAppProject.entity.enums.TransactionType;
import de.YefrAlex.BankAppProject.mapper.AccountMapper;
import de.YefrAlex.BankAppProject.mapper.TransactionMapper;
import de.YefrAlex.BankAppProject.repository.AccountRepository;
import de.YefrAlex.BankAppProject.repository.TransactionRepository;
import de.YefrAlex.BankAppProject.service.TransactionService;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.transactionRepository=transactionRepository;
        this.transactionMapper=transactionMapper;
        this.accountRepository=accountRepository;
        this.accountMapper=accountMapper;
    }

    @Override
    public List<TransactionDto> getAllTransactions() {
        List<Transaction> transactions=transactionRepository.findAll();
        return transactionMapper.mapToListDto(transactions);
    }

    @Override
    public TransactionDto getTransactionById(UUID id) {
        Transaction transaction=transactionRepository.findById(id).orElseThrow(() -> new ExpressionException("Transaction not found with id " + id));
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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Transaction createTransaction(TransactionDto transactionDto) {
        Optional <Account> debitAccountIdOptional = accountRepository.getByNumber(transactionDto.getDebitAccountNumber());
        Optional <Account> creditAccountIdOptional = accountRepository.getByNumber(transactionDto.getCreditAccountNumber());
        Transaction transaction=new Transaction();
        if (debitAccountIdOptional.isPresent()&&creditAccountIdOptional.isPresent()){
            Account debitAccountId = debitAccountIdOptional.get();
            Account creditAccountId = creditAccountIdOptional.get();
            try {
                if (checkCreditAccount(creditAccountId, transactionDto)){
                    BigDecimal newCreditBalance = creditAccountId.getBalance().subtract(transactionDto.getAmount());
                    BigDecimal newDebitBalance = debitAccountId.getBalance().add(transactionDto.getAmount());
                    debitAccountId.setBalance(newDebitBalance);
                    creditAccountId.setBalance(newCreditBalance);
                    transaction = saveTransactionFromDto(transactionDto, debitAccountId, creditAccountId);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction reversTransaction(String id) {
        Transaction transaction = transactionRepository.findById(UUID.fromString(id)).get();
        TransactionDto transactionDto = transactionMapper.toTransactionDto(transaction);
        transactionDto.setDescription("revers transaction to " + id);
        String tempAccountNumber = transactionDto.getCreditAccountNumber();
        transactionDto.setCreditAccountNumber(transactionDto.getDebitAccountNumber());
        transactionDto.setDebitAccountNumber(tempAccountNumber);

        return createTransaction(transactionDto) ;
    }

    private Transaction saveTransactionFromDto(TransactionDto transactionDto, Account debitAccountId, Account creditAccountId) {
        Transaction transaction=new Transaction();
        transaction.setId(null);
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setDebitAccountId(debitAccountId);
        transaction.setCreditAccountId(creditAccountId);
        transaction.setAmount(new BigDecimal(String.valueOf(transactionDto.getAmount())));
        transaction.setDescription(transactionDto.getDescription());
        transaction.setType(TransactionType.DEPOSIT);
        return transaction;
    }
    private Boolean checkCreditAccount (Account creditAccount, TransactionDto transactionDto) throws  Exception {
        if (creditAccount.getBalance().subtract(transactionDto.getAmount()).compareTo(BigDecimal.ZERO)<0){
            throw new  Exception("Not enough funds in the account");
        }
        return true;
    }
}
