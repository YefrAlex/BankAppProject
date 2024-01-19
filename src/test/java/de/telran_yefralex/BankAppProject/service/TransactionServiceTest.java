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
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.*;
import de.telran_yefralex.BankAppProject.mail.EmailService;
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
    @Mock
    EmailService emailServiceMok;
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
        Client client = new Client();
        client.setEmail("client@example.com");

        debitAccountId = new Account();
        debitAccountId.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        debitAccountId.setAccountNumber("debit");
        debitAccountId.setBalance(new BigDecimal("1000"));
        debitAccountId.setCurrencyCode(CurrencyCode.EUR);

        creditAccountId = new Account();
        creditAccountId.setId(UUID.fromString("52DE358F-99F1-E311-93EA-00269E58F20D"));
        creditAccountId.setAccountNumber("credit");
        creditAccountId.setClientId(client);
        creditAccountId.setBalance(new BigDecimal("1000"));
        creditAccountId.setCurrencyCode(CurrencyCode.EUR);

        expectedTransaction=new Transaction();
        expectedTransaction.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        expectedTransaction.setCreditAccountId(creditAccountId);
        expectedTransaction.setDebitAccountId(debitAccountId);
        expectedTransaction.setType(TransactionType.PAYMENT);
        expectedTransaction.setAmount(BigDecimal.valueOf(100));
        expectedTransaction.setCurrencyCode(CurrencyCode.EUR);
        expectedTransaction.setDescription("description");
        expectedTransaction.setCreatedAt(LocalDateTime.now());

        expectedTransactionDto=new TransactionDto();
        expectedTransactionDto.setCreditAccountNumber("credit");
        expectedTransactionDto.setDebitAccountNumber("debit");
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
    @Test
    void createTransaction_SuccessfulTransaction() {
        when(accountRepositoryMock.getByNumber("debit")).thenReturn(Optional.of(debitAccountId));
        when(accountRepositoryMock.getByNumber("credit")).thenReturn(Optional.of(creditAccountId));

        Transaction transaction = new Transaction();
        transaction.setDebitAccountId(debitAccountId);
        transaction.setCreditAccountId(creditAccountId);
        transaction.setAmount(new BigDecimal("100"));
        transaction.setDescription("Test transaction");
        transaction.setCurrencyCode(CurrencyCode.EUR);

        when(transactionRepositoryMock.save(any(Transaction.class))).thenReturn(expectedTransaction);

        transactionServiceTest.createTransaction(expectedTransactionDto, CurrencyCode.EUR);

        verify(accountRepositoryMock, times(1)).getByNumber("debit");
        verify(accountRepositoryMock, times(1)).getByNumber("credit");
        verify(transactionRepositoryMock, times(1)).save(any(Transaction.class));
        verify(emailServiceMok, times(1)).sendEmail(anyString(), anyString(), anyString());
    }
    @Test
    void testCheckCreditAccountInCurrency() {
        creditAccountId = mock(Account.class);
        when(creditAccountId.getBalance()).thenReturn(BigDecimal.valueOf(200));
        boolean result = transactionServiceTest.checkCreditAccountInCurrency(creditAccountId, BigDecimal.valueOf(100));
        assertTrue(result);
    }

    @Test
    void testCheckCreditAccountInCurrencyWithInsufficientFunds() {
        creditAccountId = mock(Account.class);
        when(creditAccountId.getBalance()).thenReturn(BigDecimal.valueOf(50));
        assertThrows(BalanceException.class, () -> transactionServiceTest.checkCreditAccountInCurrency(creditAccountId, BigDecimal.valueOf(100)));
    }
    @Test
    void testConvertToEuro() {
        BigDecimal result = transactionServiceTest.convertToEuro(BigDecimal.valueOf(100), CurrencyCode.USD);
        assertEquals(BigDecimal.valueOf(90.9091), result);
    }
    @Test
    void testConvertFromEuro() {
        BigDecimal result = transactionServiceTest.convertFromEuro(BigDecimal.valueOf(100), CurrencyCode.USD);
        assertEquals(BigDecimal.valueOf(110.0), result);
    }
    @Test
    void testNotifyClientTransaction() {
        transactionServiceTest.notifyClientTransaction(expectedTransaction);
        verify(emailServiceMok).sendEmail(eq("client@example.com"), eq("Transaction completed"), anyString());
    }
}
