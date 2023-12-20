package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.Agreement;
import de.telran_yefralex.BankAppProject.entity.Product;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private Product product;
    private List<Agreement> expectedAgreementList;
    private List<AgreementDto> expectedAgreementDtoList;


    @BeforeEach
    void init() {
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
