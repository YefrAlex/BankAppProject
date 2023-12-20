package de.telran_yefralex.BankAppProject.service;


import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.dto.ManagerForClientDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.ClientNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmailIsUsedException;
import de.telran_yefralex.BankAppProject.mapper.ClientMapper;
import de.telran_yefralex.BankAppProject.repository.ClientRepository;

import de.telran_yefralex.BankAppProject.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    ClientRepository clientRepositoryMock;
    @Mock
    ClientMapper clientMapperMock;
    @Mock
    PasswordEncoder passwordEncoderMock;

    @InjectMocks
    private ClientServiceImpl clientServiceTest;
    private Client expectedClient;
    private ClientShortDto expectedClientShortDto;
    private ClientFullInfoDto expectedClientFullInfoDto;
    private AccountForClientDto expectedAccountForClientDto;
    private ManagerForClientDto expectedManagerForClientDto;
    private List<AccountForClientDto> expectedAccounts;
    private List<ClientShortDto> expectedClientShortDtoList;
    private List<ClientFullInfoDto> expectedClientFullInfoDtoList;
    private List<Client> expectedClientList;


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

        expectedClientShortDto=new ClientShortDto();
        expectedClientShortDto.setFirstName("Name");
        expectedClientShortDto.setLastName("FamiliaName");
        expectedClientShortDto.setTaxCode("123456789ABC");
        expectedClientShortDto.setEmail("test@test.com");
        expectedClientShortDto.setPhone("+4912341234567");
        expectedClientShortDto.setNumberOfAccounts(1);

        expectedClientFullInfoDto=new ClientFullInfoDto();
        expectedClientFullInfoDto.setFirstName("Name");
        expectedClientFullInfoDto.setLastName("FamiliaName");
        expectedClientFullInfoDto.setTaxCode("123456789ABC");
        expectedClientFullInfoDto.setEmail("test@test.com");
        expectedClientFullInfoDto.setPassword("password");
        expectedClientFullInfoDto.setAddress("Test address");
        expectedClientFullInfoDto.setPhone("+4912341234567");
        expectedClientFullInfoDto.setCountry(Country.AUSTRIA);
        expectedClientFullInfoDto.setCreatedAt(LocalDateTime.now());
        expectedClientFullInfoDto.setUpdatedAt(LocalDateTime.now());
        expectedClientFullInfoDto.setAccounts(expectedAccounts);

        expectedAccountForClientDto=new AccountForClientDto();
        expectedAccountForClientDto.setMainManager(expectedManagerForClientDto);
        expectedAccountForClientDto.setAssistantManager(null);
        expectedAccountForClientDto.setAccountNumber("NUMBER-1111111");
        expectedAccountForClientDto.setType(String.valueOf(AccountType.OTHER));
        expectedAccountForClientDto.setBalance(BigDecimal.valueOf(1));
        expectedAccountForClientDto.setCurrencyCode(String.valueOf(CurrencyCode.EUR));

        expectedManagerForClientDto=new ManagerForClientDto();
        expectedManagerForClientDto.setFirstName("ManagerTest");
        expectedManagerForClientDto.setLastName("ManagerLastName");
        expectedManagerForClientDto.setPhone("+4911111111");
        expectedManagerForClientDto.setEmail("managerTest@test.com");

        expectedAccounts=new ArrayList<>();
        expectedAccounts.add(expectedAccountForClientDto);

        expectedClientShortDtoList=new ArrayList<>();
        expectedClientShortDtoList.add(expectedClientShortDto);

        expectedClientList=new ArrayList<>();
        expectedClientList.add(expectedClient);

        expectedClientFullInfoDtoList = new ArrayList<>();
        expectedClientFullInfoDtoList.add(expectedClientFullInfoDto);
    }


    @Test
    public void testFindAllShort() {
        // Устанавливаем поведение моков для clientRepositoryMock и clientMapperMock
        when(clientRepositoryMock.findAll()).thenReturn(expectedClientList);
        when(clientMapperMock.toClientShortDto(expectedClientList.get(0))).thenReturn(expectedClientShortDtoList.get(0));
        // Вызываем тестируемый метод
        ResponseEntity<List<ClientShortDto>> result=clientServiceTest.findAllShort();
        // Проверяем, что результат соответствует ожидаемому
        assertEquals(expectedClientShortDtoList, result.getBody());
    }
    @Test
    void testFindClientByTaxCode() {
        // Arrange
        when(clientRepositoryMock.findClientByTaxCode(anyString())).thenReturn(expectedClient);
        when(clientMapperMock.toClientShortDto(any())).thenReturn(expectedClientShortDto);
        // Act
        ClientShortDto result = clientServiceTest.findClientByTaxCode("123456789ABC");
        // Assert
        assertEquals(expectedClientShortDto, result);
    }
    @Test
    void testFindClientByTaxCode_WhenClientNotExists() {
        // Arrange
        when(clientRepositoryMock.findClientByTaxCode(anyString())).thenReturn(null);
        // Act & Assert
        assertThrows(ClientNotFoundException.class, () -> clientServiceTest.findClientByTaxCode("123456789ABC"));
    }
    @Test
    void testFindClientByEmail() {
        // Arrange
        when(clientRepositoryMock.findClientByEmail(anyString())).thenReturn(expectedClient);
        when(clientMapperMock.toClientShortDto(any())).thenReturn(expectedClientShortDto);
        // Act
        ClientShortDto result = clientServiceTest.findClientByEmail("test@test.com");
        // Assert
        assertEquals(expectedClientShortDto, result);
    }
    @Test
    void testFindClientByEmail_WhenClientNotExists() {
        // Arrange
        when(clientRepositoryMock.findClientByEmail(anyString())).thenReturn(null);
        // Act & Assert
        assertThrows(ClientNotFoundException.class, () -> clientServiceTest.findClientByEmail("test@test.com"));
    }
    @Test
    public void testFindAllFullInfo() {
        // Устанавливаем поведение моков для clientRepositoryMock и clientMapperMock
        when(clientRepositoryMock.findAll()).thenReturn(expectedClientList);
        when(clientMapperMock.toClientFullInfoDto(expectedClientList.get(0))).thenReturn(expectedClientFullInfoDtoList.get(0));
        // Вызываем тестируемый метод
        List<ClientFullInfoDto> result=clientServiceTest.findAllFullInfo();
        // Проверяем, что результат соответствует ожидаемому
        assertEquals(expectedClientFullInfoDtoList, result);
    }
    @Test
    public void testUpdateClient() {
        // Устанавливаем поведение моков для clientRepositoryMock
        when(clientRepositoryMock.findClientByTaxCode(anyString())).thenReturn(expectedClient);
        // Вызываем тестируемый метод
        clientServiceTest.updateClient(
                "123456789ABC", "NewName", "NewLastName", "new@test.com", "New Address", "+491234567890", Country.GERMANY, true);

        // Проверяем, что методы репозитория были вызваны с правильными параметрами
        verify(clientRepositoryMock).findClientByTaxCode("123456789ABC");
        verify(clientRepositoryMock).updateClient(
                "123456789ABC", "NewName", "NewLastName", "new@test.com", "New Address", "+491234567890", Country.GERMANY);

        // Проверяем, что метод save был вызван, так как isBlocked == true
        verify(clientRepositoryMock).save(any(Client.class));
    }

    @Test
    public void testUpdateClient_ClientNotFound() {
        // Устанавливаем поведение мока для clientRepositoryMock
        when(clientRepositoryMock.findClientByTaxCode(anyString())).thenReturn(null);
        // Вызываем тестируемый метод и ожидаем исключение
        assertThrows(ClientNotFoundException.class, () -> {
            clientServiceTest.updateClient("NonExistentTaxCode", "NewName", "NewLastName",
                    "new@test.com", "New Address", "+491234567890", Country.GERMANY, true);
        });
        // Проверяем, что метод findClientByTaxCode был вызван
        verify(clientRepositoryMock).findClientByTaxCode("NonExistentTaxCode");
        // Проверяем, что метод updateClient и save не были вызваны
        verify(clientRepositoryMock, never()).updateClient(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), any(Country.class));
        verify(clientRepositoryMock, never()).save(any(Client.class));
    }
    @Test
    public void testCreateNewClient() {
        // Устанавливаем поведение моков для clientRepositoryMock, clientMapperMock и passwordEncoderMock
        when(clientRepositoryMock.findClientByEmail(anyString())).thenReturn(null);
        when(clientMapperMock.toClient(any(ClientFullInfoDto.class))).thenReturn(expectedClient);
        when(passwordEncoderMock.encode(anyString())).thenReturn("password");
        when(clientRepositoryMock.save(any(Client.class))).thenReturn(expectedClient);
        Client result = clientServiceTest.createNewClient(expectedClientFullInfoDto);
        // Проверяем, что результат соответствует ожидаемому
        assertEquals(expectedClient, result);
        // Проверяем, что методы моков были вызваны с правильными параметрами
        verify(clientRepositoryMock).findClientByEmail(expectedClientFullInfoDto.getEmail());
        verify(clientMapperMock).toClient(expectedClientFullInfoDto);
        verify(passwordEncoderMock).encode(expectedClientFullInfoDto.getPassword());
        verify(clientRepositoryMock).save(expectedClient);
    }
    @Test
    public void testCreateNewClient_EmailIsUsedException() {
        // Устанавливаем поведение моков для clientRepositoryMock
        when(clientRepositoryMock.findClientByEmail(anyString())).thenReturn(expectedClient);
        // Вызываем тестируемый метод и ожидаем исключение
        // ClientFullInfoDto clientFullInfoDto = expectedClientFullInfoDto; // заполните объект данными
        assertThrows(EmailIsUsedException.class, () -> {
            clientServiceTest.createNewClient(expectedClientFullInfoDto);
        });

        // Проверяем, что метод findClientByEmail был вызван
        verify(clientRepositoryMock).findClientByEmail(expectedClientFullInfoDto.getEmail());
        // Проверяем, что методы clientMapperMock, passwordEncoderMock и clientRepositoryMock не были вызваны
        verify(clientMapperMock, never()).toClient(any(ClientFullInfoDto.class));
        verify(passwordEncoderMock, never()).encode(anyString());
        verify(clientRepositoryMock, never()).save(any(Client.class));
    }
}




