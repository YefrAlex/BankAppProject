package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.*;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.AccountNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.ClientNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyAccountsListException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyEmployeeListException;
import de.telran_yefralex.BankAppProject.mapper.AccountMapper;
import de.telran_yefralex.BankAppProject.repository.AccountRepository;
import de.telran_yefralex.BankAppProject.repository.ClientRepository;
import de.telran_yefralex.BankAppProject.repository.EmployeeRepository;
import de.telran_yefralex.BankAppProject.service.impl.AccountServiceImpl;
import de.telran_yefralex.BankAppProject.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepositoryMock;
    @Mock
    AccountMapper accountMapperMock;
    @Mock
    ClientRepository clientRepositoryMock;
    @Mock
    EmployeeRepository employeeRepositoryMock;

    @InjectMocks
    private AccountServiceImpl accountServiceTest;
    private Account expectedAccount;
    private AccountDto expectedAccountDto;
    private AccountForClientDto expectedAccountForClientDto;
    private List<Account> expectedAccountList;
    private List<AccountDto> expectedAccountDtoList;
    private List<AccountForClientDto> expectedAccountForClientDtoList;
    private Client client;
    private Employee mainManager;
    private Employee assistantManager;
    private ManagerForClientDto mainManagerDto;
    private ManagerForClientDto assistantManagerDto;

    @BeforeEach
    void init() {
        expectedAccount=new Account();
        expectedAccount.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        expectedAccount.setClientId(client);
        expectedAccount.setMainManagerId(mainManager);
        expectedAccount.setAssistantManagerId(assistantManager);
        expectedAccount.setAccountNumber("accountNumber");
        expectedAccount.setType(AccountType.CREDIT);
        expectedAccount.setBalance(BigDecimal.valueOf(1));
        expectedAccount.setCurrencyCode(CurrencyCode.EUR);
        expectedAccount.setCreatedAt(LocalDateTime.now());
        expectedAccount.setUpdatedAt(LocalDateTime.now());
        expectedAccount.setBlocked(false);

        expectedAccountDto=new AccountDto();
        expectedAccountDto.setTaxCode("1234567");
        expectedAccountDto.setMainManager("mainManager");
        expectedAccountDto.setAssistantManager("assistantManager");
        expectedAccountDto.setAccountNumber("accountNumber");
        expectedAccountDto.setType(AccountType.CREDIT);
        expectedAccountDto.setBalance(BigDecimal.valueOf(1));
        expectedAccountDto.setCurrencyCode(CurrencyCode.EUR);

        expectedAccountForClientDto=new AccountForClientDto();
        expectedAccountForClientDto.setMainManager(mainManagerDto);
        expectedAccountForClientDto.setAssistantManager(assistantManagerDto);
        expectedAccountForClientDto.setAccountNumber("accountNumber");
        expectedAccountForClientDto.setType("CREDIT");
        expectedAccountForClientDto.setBalance(BigDecimal.valueOf(1));
        expectedAccountForClientDto.setCurrencyCode("EUR");

        expectedAccountList=new ArrayList<>();
        expectedAccountList.add(expectedAccount);

        expectedAccountDtoList=new ArrayList<>();
        expectedAccountDtoList.add(expectedAccountDto);

        expectedAccountForClientDtoList=new ArrayList<>();
        expectedAccountForClientDtoList.add(expectedAccountForClientDto);
    }

    @Test
    public void testFindAll() {
        when(accountRepositoryMock.findAll()).thenReturn(expectedAccountList);
        when(accountMapperMock.toAccountDto(expectedAccountList.get(0))).thenReturn(expectedAccountDtoList.get(0));
        List<AccountDto> result=accountServiceTest.findAll();
        assertEquals(expectedAccountDtoList, result);
    }

    @Test
    public void testFindAll_EmptyListException() {
        when(accountRepositoryMock.findAll()).thenReturn(List.of());
        EmptyAccountsListException exception=assertThrows(EmptyAccountsListException.class, () -> {
            accountServiceTest.findAll();
        });
        assertEquals(ErrorMessage.EMPTY_ACCOUNTS_LIST, exception.getMessage());
        verify(accountRepositoryMock).findAll();
        verifyNoInteractions(accountMapperMock);
    }

    @Test
    public void testFindAllClientsAccount() {
        when(accountRepositoryMock.findAll()).thenReturn(expectedAccountList);
        when(accountMapperMock.toAccountForClientDto(expectedAccountList.get(0))).thenReturn(expectedAccountForClientDtoList.get(0));
        List<AccountForClientDto> result=accountServiceTest.findAllClientsAccount();
        assertEquals(expectedAccountForClientDtoList, result);
    }

    @Test
    public void testFindAllClientsAccount_EmptyListException() {
        when(accountRepositoryMock.findAll()).thenReturn(List.of());
        EmptyAccountsListException exception=assertThrows(EmptyAccountsListException.class, () -> {
            accountServiceTest.findAll();
        });
        assertEquals(ErrorMessage.EMPTY_ACCOUNTS_LIST, exception.getMessage());
        verify(accountRepositoryMock).findAll();
        verifyNoInteractions(accountMapperMock);
    }

    @Test
    void testGetAccountByNumber() {
        when(accountRepositoryMock.getByNumber(anyString())).thenReturn(Optional.ofNullable(expectedAccount));
        when(accountMapperMock.toAccountDto(any())).thenReturn(expectedAccountDto);
        AccountDto result=accountServiceTest.getAccountByNumber("accountNumber");
        assertEquals(expectedAccountDto, result);
    }
    @Test
    void testFindAccountByNumber_WhenAccountNotExists() {
        when(accountRepositoryMock.getByNumber(anyString())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> accountServiceTest.getAccountByNumber("NotExistNumber"));
        verify(accountRepositoryMock).getByNumber("NotExistNumber");
        verify(accountMapperMock, never()).toAccountDto(any());
    }
    @Test
    void testSaveAccount() {
        when(clientRepositoryMock.findClientByTaxCode(expectedAccountDto.getTaxCode())).thenReturn(client);
        when(employeeRepositoryMock.findEmployeeByEmail(expectedAccountDto.getMainManager())).thenReturn(mainManager);
        when(employeeRepositoryMock.findEmployeeByEmail(expectedAccountDto.getAssistantManager())).thenReturn(assistantManager);
        Account result = accountServiceTest.saveAccount(expectedAccountDto);
        assertEquals(client, result.getClientId());
        assertEquals(mainManager, result.getMainManagerId());
        assertEquals(assistantManager, result.getAssistantManagerId());
        assertEquals(expectedAccountDto.getAccountNumber(), result.getAccountNumber());
        assertEquals(expectedAccountDto.getType(), result.getType());
        assertEquals(expectedAccountDto.getBalance(), result.getBalance());
        assertEquals(expectedAccountDto.getCurrencyCode(), result.getCurrencyCode());
        result.setCreatedAt(LocalDateTime.now());
        result.setUpdatedAt(LocalDateTime.now());
        result.setBlocked(false);
        verify(accountRepositoryMock).save(result);
    }
    @Test
    void testUpdateAccount (){
        when(accountRepositoryMock.getByNumber(anyString())).thenReturn(Optional.ofNullable(expectedAccount));
        accountServiceTest.updateAccount("accountNumber", "mainManagerEmail",
                "assistantManagerEmail", AccountType.CREDIT, CurrencyCode.EUR, false);
        assertEquals(mainManager, expectedAccount.getMainManagerId());
        assertEquals(assistantManager, expectedAccount.getAssistantManagerId());
        assertEquals(AccountType.CREDIT, expectedAccount.getType());
        assertEquals(CurrencyCode.EUR, expectedAccount.getCurrencyCode());
        assertEquals(false, expectedAccount.isBlocked());
        verify(accountRepositoryMock).save(expectedAccount);
    }
    @Test
    public void testUpdateAccount_WhenAccountNotExists() {
        when(accountRepositoryMock.getByNumber(anyString())).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> {
            accountServiceTest.updateAccount("NotExistentAccount", "mainManagerEmail", "assistantManagerEmail", AccountType.CREDIT, CurrencyCode.EUR, false);
        });
        verify(accountRepositoryMock).getByNumber("NotExistentAccount");
        verify(employeeRepositoryMock,never()).findEmployeeByEmail(anyString());
        verify(accountRepositoryMock,never()).save(any());
    }


}
