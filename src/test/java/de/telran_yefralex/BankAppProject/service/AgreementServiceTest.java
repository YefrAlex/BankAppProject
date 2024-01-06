package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import de.telran_yefralex.BankAppProject.entity.*;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.AccountNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyAgreementsListException;
import de.telran_yefralex.BankAppProject.mapper.AgreementMapper;
import de.telran_yefralex.BankAppProject.repository.AccountRepository;
import de.telran_yefralex.BankAppProject.repository.AgreementRepository;
import de.telran_yefralex.BankAppProject.repository.ProductRepository;
import de.telran_yefralex.BankAppProject.service.impl.AgreementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AgreementServiceTest {
    @Mock
    AgreementRepository agreementRepositoryMock;
    @Mock
    AgreementMapper agreementMapperMock;
    @Mock
    AccountRepository accountRepositoryMock;
    @Mock
    ProductRepository productRepositoryMock;

    @InjectMocks
    private AgreementServiceImpl agreementServiceTest;

    private Agreement expectedAgreement;
    private AgreementDto expectedAgreementDto;
    private Account account;
    private Account expectedAccount;
    private Client expectedClient;
    private Employee mainManager;
    private Employee assistantManager;
    private Product product;
    private List<Agreement> expectedAgreementList;
    private List<AgreementDto> expectedAgreementDtoList;


    @BeforeEach
    void init() {
        expectedClient=new Client();
        expectedClient.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        expectedClient.setFirstName("Name");
        expectedClient.setLastName("FamiliaName");
        expectedClient.setTaxCode("123456789ABC");
        expectedClient.setPassword("password");
        expectedClient.setCreditRating(1);
        expectedClient.setEmail("test@test.com");
        expectedClient.setPhone("+4912341234567");
        expectedClient.setAddress("Test address");
        expectedClient.setCountry(Country.AUSTRIA);
        expectedClient.setCreatedAt(LocalDateTime.now());
        expectedClient.setUpdatedAt(LocalDateTime.now());
        expectedClient.setBlocked(false);
        expectedClient.setAccounts(new HashSet<>());

        expectedAccount=new Account();
        expectedAccount.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        expectedAccount.setClientId(expectedClient);
        expectedAccount.setMainManagerId(mainManager);
        expectedAccount.setAssistantManagerId(assistantManager);
        expectedAccount.setAccountNumber("accountNumber");
        expectedAccount.setType(AccountType.CREDIT);
        expectedAccount.setBalance(BigDecimal.valueOf(1));
        expectedAccount.setCurrencyCode(CurrencyCode.EUR);
        expectedAccount.setCreatedAt(LocalDateTime.now());
        expectedAccount.setUpdatedAt(LocalDateTime.now());
        expectedAccount.setBlocked(false);

        expectedAgreement=new Agreement();
        expectedAgreement.setId(1l);
        expectedAgreement.setAccountId(account);
        expectedAgreement.setProductId(product);
        expectedAgreement.setInterestRate(BigDecimal.valueOf(1));
        expectedAgreement.setAmount(BigDecimal.valueOf(100));
        expectedAgreement.setDuration(12);
        expectedAgreement.setDescription("description");
        expectedAgreement.setCreatedAt(LocalDateTime.now());
        expectedAgreement.setUpdateAt(LocalDateTime.now());
        expectedAgreement.setIsBlocked(false);

        expectedAgreementDto=new AgreementDto();
        expectedAgreementDto.setId(1l);
        expectedAgreementDto.setAccountNumber("accountNumber");
        expectedAgreementDto.setProductId(1l);
        expectedAgreementDto.setInterestRate(BigDecimal.valueOf(1));
        expectedAgreementDto.setAmount(BigDecimal.valueOf(100));
        expectedAgreementDto.setDuration(12);
        expectedAgreementDto.setDescription("description");
        expectedAgreementDto.setCreatedAt(LocalDateTime.now());
        expectedAgreementDto.setUpdateAt(LocalDateTime.now());
        expectedAgreementDto.setBlocked(false);

        expectedAgreementList=new ArrayList<>();
        expectedAgreementList.add(expectedAgreement);

        expectedAgreementDtoList=new ArrayList<>();
        expectedAgreementDtoList.add(expectedAgreementDto);

    }

    @Test
    public void testFindAll() {
        when(agreementRepositoryMock.findAll()).thenReturn(expectedAgreementList);
        when(agreementMapperMock.toAgreementDto(expectedAgreementList.get(0))).thenReturn(expectedAgreementDtoList.get(0));
        List<AgreementDto> result=agreementServiceTest.findAll();
        assertEquals(expectedAgreementDtoList, result);
    }

    @Test
    public void testFindAll_EmptyAgreementListException() {
        when(agreementRepositoryMock.findAll()).thenReturn(List.of());
        EmptyAgreementsListException exception=assertThrows(EmptyAgreementsListException.class, () -> {
            agreementServiceTest.findAll();
        });
        assertEquals(ErrorMessage.EMPTY_AGREEMENTS_LIST, exception.getMessage());
        verify(agreementRepositoryMock).findAll();
        verifyNoInteractions(agreementMapperMock);
    }

    @Test
    public void testFindMyAgreement() {
        when(agreementRepositoryMock.findMyAgreement(anyString())).thenReturn(expectedAgreementList);
        when(agreementMapperMock.toAgreementDto(expectedAgreementList.get(0))).thenReturn(expectedAgreementDtoList.get(0));
        List<AgreementDto> result=agreementServiceTest.findMyAgreement("test@test.com");
        assertEquals(expectedAgreementDtoList, result);
    }

    @Test
    public void testFindMyAgreement_EmptyAgreementListException() {
        when(agreementRepositoryMock.findMyAgreement(anyString())).thenReturn(List.of());
        EmptyAgreementsListException exception=assertThrows(EmptyAgreementsListException.class, () -> {
            agreementServiceTest.findMyAgreement("test@test.com");
        });
        assertEquals(ErrorMessage.EMPTY_AGREEMENTS_LIST, exception.getMessage());
        verify(agreementRepositoryMock).findMyAgreement(anyString());
        verifyNoInteractions(agreementMapperMock);
    }

    @Test
    void testSaveAgreement() {
        Account account=new Account();
        Product product=new Product();
        when(accountRepositoryMock.getByNumber("accountNumber")).thenReturn(Optional.of(account));
        when(productRepositoryMock.findById(1L)).thenReturn(Optional.of(product));
        Agreement result=agreementServiceTest.saveAgreement(expectedAgreementDto);
        assertEquals(account, result.getAccountId());
        assertEquals(product, result.getProductId());
        assertEquals(BigDecimal.valueOf(1), result.getInterestRate());
        assertEquals(BigDecimal.valueOf(100), result.getAmount());
        assertEquals(12, result.getDuration());
        assertEquals("description", result.getDescription());
        verify(accountRepositoryMock).getByNumber("accountNumber");
        verify(productRepositoryMock).findById(1L);
        verify(agreementRepositoryMock).save(result);
    }

    @Test
    void testSaveAgreement_AccountNotFound() {
        expectedAgreementDto.setAccountNumber("nonExistentAccount");
        when(accountRepositoryMock.getByNumber("nonExistentAccount")).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class, () -> {
            agreementServiceTest.saveAgreement(expectedAgreementDto);
        });
        verify(accountRepositoryMock).getByNumber("nonExistentAccount");
        verify(productRepositoryMock, never()).findById(1L);
        verify(agreementRepositoryMock, never()).save(expectedAgreement);
    }

    @Test
    void testUpdateAgreement() {
        when(agreementRepositoryMock.findById(1L)).thenReturn(Optional.of(expectedAgreement));
        agreementServiceTest.updateAgreement(1L, BigDecimal.valueOf(2), BigDecimal.valueOf(200), 24, true);
        assertEquals(BigDecimal.valueOf(2), expectedAgreement.getInterestRate());
        assertEquals(BigDecimal.valueOf(200), expectedAgreement.getAmount());
        assertEquals(24, expectedAgreement.getDuration());
        assertEquals(true, expectedAgreement.getIsBlocked());
        assertNotNull(expectedAgreement.getUpdateAt());
        verify(agreementRepositoryMock).findById(1L);
        verify(agreementRepositoryMock).save(expectedAgreement);
    }
}
