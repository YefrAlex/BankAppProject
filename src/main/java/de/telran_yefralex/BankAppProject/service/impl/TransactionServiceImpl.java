package de.telran_yefralex.BankAppProject.service.impl;

import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.Transaction;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.entity.enums.TransactionType;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.AccountNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.BalanceException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyTransactionsListException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.TransactionNotFoundException;
import de.telran_yefralex.BankAppProject.mapper.AccountMapper;
import de.telran_yefralex.BankAppProject.mapper.TransactionMapper;
import de.telran_yefralex.BankAppProject.repository.AccountRepository;
import de.telran_yefralex.BankAppProject.repository.TransactionRepository;
import de.telran_yefralex.BankAppProject.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        if (transactions == null) {
            throw new EmptyTransactionsListException(ErrorMessage.EMPTY_TRANSACTIONS_LIST);
        }
        return transactionMapper.mapToListDto(transactions);
    }

    @Override
    public TransactionDto getTransactionById(UUID id) {
        Transaction transaction=transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(String.format(ErrorMessage.TRANSACTION_NOT_FOUND, id)));
        return transactionMapper.toTransactionDto(transaction);
    }

    @Override
    public List<TransactionDto> getAllTransactionsOfAccount(String accountNumber) {
        List<Transaction> transactions=transactionRepository
                .getAllTransactionsWithAccountNumber(accountNumber);
        if (transactions == null) {
            throw new EmptyTransactionsListException(ErrorMessage.EMPTY_TRANSACTIONS_LIST + "check that the accountNumber is correct");
        }
        return transactionMapper.mapToListDto(transactions);
    }

    @Override
    public List<TransactionDto> getAllTransactionsOfTaxCode(String taxCode) {
        List<Transaction> transactions=transactionRepository
                .getAllTransactionsWithTaxCode(taxCode);
        if (transactions == null) {
            throw new EmptyTransactionsListException(ErrorMessage.EMPTY_TRANSACTIONS_LIST + "check that the taxCode is correct");
        }
        return transactionMapper.mapToListDto(transactions);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Transaction createTransaction(TransactionDto transactionDto) {
        Optional<Account> debitAccountOptional = accountRepository.getByNumber(transactionDto.getDebitAccountNumber());
        Optional<Account> creditAccountOptional = accountRepository.getByNumber(transactionDto.getCreditAccountNumber());
        Transaction transaction = new Transaction();

        if (debitAccountOptional.isPresent() && creditAccountOptional.isPresent()) {
            Account debitAccount = debitAccountOptional.get();
            Account creditAccount = creditAccountOptional.get();
            BigDecimal amountInEuro = convertToEuro(transactionDto.getAmount(), debitAccount.getCurrencyCode());
            if (checkCreditAccount(creditAccount, amountInEuro)) {

                BigDecimal newCreditBalance = creditAccount.getBalance().subtract(transactionDto.getAmount());
                BigDecimal newDebitBalance = debitAccount.getBalance().add(convertFromEuro(amountInEuro,debitAccount.getCurrencyCode()));

                debitAccount.setBalance(newDebitBalance);
                creditAccount.setBalance(newCreditBalance);

                transaction = saveTransactionFromDto(transactionDto, debitAccount, creditAccount);
            }
        }  else throw new AccountNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND);

        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    public Transaction reversTransaction(String id) {
        Transaction transaction=transactionRepository.findById(UUID.fromString(id)).orElseThrow(() -> new TransactionNotFoundException(String.format(ErrorMessage.TRANSACTION_NOT_FOUND, id)));
        TransactionDto transactionDto=transactionMapper.toTransactionDto(transaction);
        transactionDto.setDescription("revers transaction to " + id);
        String tempAccountNumber=transactionDto.getCreditAccountNumber();
        transactionDto.setCreditAccountNumber(transactionDto.getDebitAccountNumber());
        transactionDto.setDebitAccountNumber(tempAccountNumber);
        return createTransaction(transactionDto);
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

    private Boolean checkCreditAccount(Account creditAccount, BigDecimal amountInEuro) {
        BigDecimal creditBalanceInEuro = convertToEuro(creditAccount.getBalance(), creditAccount.getCurrencyCode());
        if (creditBalanceInEuro.subtract(amountInEuro).compareTo(BigDecimal.ZERO) < 0) {
            throw new BalanceException(ErrorMessage.BALANCE_EX);
        }
        return true;
    }

    private BigDecimal convertToEuro(BigDecimal amount, CurrencyCode currencyCode) {
        double exchangeRateToEuro=currencyCode.getExchangeRateToEuro();
        return amount.divide(BigDecimal.valueOf(exchangeRateToEuro), 4, RoundingMode.HALF_UP);
    }
    private BigDecimal convertFromEuro(BigDecimal amount, CurrencyCode currencyCode) {
        double exchangeRateToEuro=currencyCode.getExchangeRateToEuro();
        return amount.multiply(BigDecimal.valueOf(exchangeRateToEuro));
    }
}


