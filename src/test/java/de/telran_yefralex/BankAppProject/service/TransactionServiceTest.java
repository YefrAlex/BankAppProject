package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.Transaction;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.entity.enums.TransactionType;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.AccountNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyAgreementsListException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyTransactionsListException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.TransactionNotFoundException;
import de.telran_yefralex.BankAppProject.mapper.TransactionMapper;
import de.telran_yefralex.BankAppProject.repository.AccountRepository;
import de.telran_yefralex.BankAppProject.repository.TransactionRepository;
import de.telran_yefralex.BankAppProject.service.impl.AgreementServiceImpl;
import de.telran_yefralex.BankAppProject.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    TransactionRepository transactionRepositoryMock;
    @Mock
    TransactionMapper transactionMapperMock;
    @Mock
    AccountRepository accountRepositoryMock;
    @InjectMocks
    private TransactionServiceImpl transactionServiceTest;

    private Transaction expectedTransaction;
    private TransactionDto expectedTransactionDto;
    private Account debitAccountId;
    private Account creditAccountId;
    private Client client;
    private Employee mainManager;
    private Employee assistantManager;
    List<Transaction> expectedTransactionList;
    List<TransactionDto> expectedTransactionDtoList;

    @BeforeEach
    void init() {
        expectedTransaction=new Transaction();
        expectedTransaction.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        expectedTransaction.setCreditAccountId(creditAccountId);
        expectedTransaction.setDebitAccountId(debitAccountId);
        expectedTransaction.setType(TransactionType.PAYMENT);
        expectedTransaction.setAmount(BigDecimal.valueOf(100));
        expectedTransaction.setDescription("description");
        expectedTransaction.setCreatedAt(LocalDateTime.now());

        expectedTransactionDto=new TransactionDto();
        expectedTransactionDto.setCreditAccountNumber("creditAccountId");
        expectedTransactionDto.setDebitAccountNumber("debitAccountId");
        expectedTransactionDto.setAmount(BigDecimal.valueOf(100));
        expectedTransactionDto.setDescription("description");
        expectedTransactionDto.setCreatedAt(LocalDateTime.now());

        expectedTransactionList=new ArrayList<>();
        expectedTransactionList.add(expectedTransaction);

        expectedTransactionDtoList=new ArrayList<>();
        expectedTransactionDtoList.add(expectedTransactionDto);
    }

    @Test
    public void getAllTransactions() {
        when(transactionRepositoryMock.findAll()).thenReturn(expectedTransactionList);
        when(transactionMapperMock.mapToListDto(expectedTransactionList)).thenReturn(expectedTransactionDtoList);
        List<TransactionDto> result=transactionServiceTest.getAllTransactions();
        assertEquals(expectedTransactionDtoList, result);
    }

    @Test
    public void getAllTransactions_EmptyTransactionsListException() {
        when(transactionRepositoryMock.findAll()).thenReturn(null);
        EmptyTransactionsListException exception=assertThrows(EmptyTransactionsListException.class, () -> {
            transactionServiceTest.getAllTransactions();
        });
        assertEquals(ErrorMessage.EMPTY_TRANSACTIONS_LIST, exception.getMessage());
        verify(transactionRepositoryMock).findAll();
        verifyNoInteractions(transactionMapperMock);
    }

    @Test
    public void getTransactionById() {
        when(transactionRepositoryMock.findById(any(UUID.class))).thenReturn(Optional.ofNullable(expectedTransaction));
        when(transactionMapperMock.toTransactionDto(expectedTransaction)).thenReturn(expectedTransactionDto);
        TransactionDto result=transactionServiceTest.getTransactionById(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        assertEquals(expectedTransactionDto, result);
    }

    @Test
    public void getTransactionById_TransactionNotFound() {
        when(transactionRepositoryMock.findById(UUID.fromString("99DE358F-45F1-E311-93EA-00269E58F20D"))).thenReturn(Optional.empty());
        assertThrows(TransactionNotFoundException.class, () -> {
            transactionServiceTest.getTransactionById(UUID.fromString("99DE358F-45F1-E311-93EA-00269E58F20D"));
        });
        verify(transactionRepositoryMock).findById(UUID.fromString("99DE358F-45F1-E311-93EA-00269E58F20D"));
    }

    @Test
    public void testGetAllTransactionsOfAccount() {
        when(transactionRepositoryMock.getAllTransactionsWithAccountNumber(anyString())).thenReturn(expectedTransactionList);
        when(transactionMapperMock.mapToListDto(expectedTransactionList)).thenReturn(expectedTransactionDtoList);
        List<TransactionDto> result=transactionServiceTest.getAllTransactionsOfAccount("accountNumber");
        assertEquals(expectedTransactionDtoList, result);
    }
    @Test
    public void testGetAllTransactionsOfAccount_EmptyListException(){
        when(transactionRepositoryMock.getAllTransactionsWithAccountNumber("non_existed")).thenReturn(null);
        assertThrows(EmptyTransactionsListException.class,
                () -> transactionServiceTest.getAllTransactionsOfAccount("non_existed"),
                ErrorMessage.EMPTY_TRANSACTIONS_LIST);
    }
    @Test
    public void testGetAllTransactionsOfTaxCode() {
        when(transactionRepositoryMock.getAllTransactionsWithTaxCode(anyString())).thenReturn(expectedTransactionList);
        when(transactionMapperMock.mapToListDto(expectedTransactionList)).thenReturn(expectedTransactionDtoList);
        List<TransactionDto> result=transactionServiceTest.getAllTransactionsOfTaxCode("taxCode");
        assertEquals(expectedTransactionDtoList, result);
    }
    @Test
    public void testGetAllTransactionsOfTaxCode_EmptyListException(){
        when(transactionRepositoryMock.getAllTransactionsWithTaxCode("non_existed_taxCode")).thenReturn(null);
        assertThrows(EmptyTransactionsListException.class,
                () -> transactionServiceTest.getAllTransactionsOfTaxCode("non_existed_taxCode"),
                ErrorMessage.EMPTY_TRANSACTIONS_LIST);
    }
    @Test
    public void testSaveTransactionFromDto_SuccessfulSave() {
        Account debitAccount = new Account();
        debitAccount.setId(UUID.randomUUID());
        Account creditAccount = new Account();
        creditAccount.setId(UUID.randomUUID());
        Transaction result = transactionServiceTest.saveTransactionFromDto(expectedTransactionDto, debitAccount, creditAccount);
        assertNotNull(result);
        assertNull(result.getId());
        assertNotNull(result.getCreatedAt());
        assertEquals(debitAccount, result.getDebitAccountId());
        assertEquals(creditAccount, result.getCreditAccountId());
        assertEquals(expectedTransactionDto.getAmount(), result.getAmount());
        assertEquals(expectedTransactionDto.getDescription(), result.getDescription());
        assertEquals(TransactionType.DEPOSIT, result.getType());
    }
}
