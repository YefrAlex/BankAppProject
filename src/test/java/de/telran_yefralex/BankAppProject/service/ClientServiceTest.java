package de.telran_yefralex.BankAppProject.service;


import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.dto.ManagerForClientDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    ClientRepository clientRepositoryMock;
    @Mock
    ClientMapper clientMapperMock;

    @InjectMocks
    private ClientServiceImpl clientServiceTest;
    private Client expectedClient;
    private ClientShortDto expectedClientShortDto;
    private ClientFullInfoDto expectedClientFullInfoDto;
    private AccountForClientDto expectedAccountForClientDto;
    private ManagerForClientDto expectedManagerForClientDto;
    private List<AccountForClientDto> expectedAccounts;
    private List<ClientShortDto> expectedClientShortDtoList;
    private List<Client> expectedClientList;


    @BeforeEach
    void init() {
        expectedClient=new Client();
        expectedClient.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        expectedClient.setFirstName("Name");
        expectedClient.setLastName("FamiliaName");
        expectedClient.setTaxCode("123456789ABC");
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
    }


    @Test
    public void testFindAllShort() {
        // Устанавливаем поведение моков для clientRepositoryMock и clientMapperMock
        when(clientRepositoryMock.findAll()).thenReturn(expectedClientList);
        when(clientMapperMock.toClientShortDto(expectedClientList.get(0))).thenReturn(expectedClientShortDtoList.get(0));

        // Вызываем тестируемый метод
        ResponseEntity<List<ClientShortDto>> result=clientServiceTest.findAllShort();

        // Проверяем, что результат соответствует ожидаемому
        assertEquals(expectedClientShortDtoList, result);
    }

}


