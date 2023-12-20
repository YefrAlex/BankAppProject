package de.telran_yefralex.BankAppProject.service;


import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.dto.EmployeeDto;
import de.telran_yefralex.BankAppProject.dto.ManagerForClientDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.ClientNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmailIsUsedException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmployeeNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyEmployeeListException;
import de.telran_yefralex.BankAppProject.mapper.ManagerMapper;
import de.telran_yefralex.BankAppProject.repository.EmployeeRepository;
import de.telran_yefralex.BankAppProject.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepositoryMock;
    @Mock
    ManagerMapper managerMapperMock;
    @Mock
    PasswordEncoder passwordEncoderMock;
    @InjectMocks
    private EmployeeServiceImpl employeeServiceTest;
    private Employee expectedEmployee;
    private EmployeeDto expectedEmployeeDto;
    private ManagerForClientDto expectedManagerForClientDto;
    private List<EmployeeDto> expectedEmployeeDtoList;
    private List<Employee> expectedEmployeeList;


    @BeforeEach
    void init() {
        expectedEmployee = new Employee();
        expectedEmployee.setId(UUID.fromString("52DE358F-45F1-E311-93EA-00269E58F20D"));
        expectedEmployee.setFirstName("Name");
        expectedEmployee.setLastName("FamiliaName");
        expectedEmployee.setRole(Role.MANAGER);
        expectedEmployee.setEmail("test@test.com");
        expectedEmployee.setPassword("password");
        expectedEmployee.setEmail("test@test.com");
        expectedEmployee.setPhone("+4912341234567");
        expectedEmployee.setCountry(Country.AUSTRIA);
        expectedEmployee.setCreatedAt(LocalDateTime.now());
        expectedEmployee.setUpdatedAt(LocalDateTime.now());
        expectedEmployee.setBlocked(false);
        expectedEmployee.setMainManagerAccounts(new HashSet<>());
        expectedEmployee.setAssistantManagerAccounts(new HashSet<>());

        expectedEmployeeDto = new EmployeeDto();
        expectedEmployeeDto.setFirstName("Name");
        expectedEmployeeDto.setLastName("FamiliaName");
        expectedEmployeeDto.setRole(Role.MANAGER);
        expectedEmployeeDto.setEmail("test@test.com");
        expectedEmployeeDto.setPassword("password");
        expectedEmployeeDto.setPhone("+4912341234567");
        expectedEmployeeDto.setCountry(Country.AUSTRIA);
        expectedEmployeeDto.setCreatedAt(LocalDateTime.now());
        expectedEmployeeDto.setUpdatedAt(LocalDateTime.now());
        expectedEmployeeDto.setBlocked(false);

        expectedManagerForClientDto = new ManagerForClientDto();
        expectedManagerForClientDto.setFirstName("Name");
        expectedManagerForClientDto.setLastName("FamiliaName");
        expectedManagerForClientDto.setEmail("test@test.com");
        expectedManagerForClientDto.setPhone("+4912341234567");

        expectedEmployeeList = new ArrayList<>();
        expectedEmployeeList.add(expectedEmployee);

        expectedEmployeeDtoList = new ArrayList<>();
        expectedEmployeeDtoList.add(expectedEmployeeDto);
    }
    @Test
    public void testGetAll() {
        when(employeeRepositoryMock.findAll()).thenReturn(expectedEmployeeList);
        when(managerMapperMock.toEmployeeDto(expectedEmployeeList.get(0))).thenReturn(expectedEmployeeDtoList.get(0));
        List<EmployeeDto> result = employeeServiceTest.getAll();
        assertEquals(expectedEmployeeDtoList, result);
    }
    @Test
    public void testGetAll_EmptyListException() {
        when(employeeRepositoryMock.findAll()).thenReturn(List.of());
        EmptyEmployeeListException exception = assertThrows(EmptyEmployeeListException.class, () -> {
            employeeServiceTest.getAll();
        });
        assertEquals(ErrorMessage.EMPTY_EMPLOYEES_LIST, exception.getMessage());
        verify(employeeRepositoryMock).findAll();
        verifyNoInteractions(managerMapperMock);
    }
    @Test
    void testGetAllByRole() {
        when(employeeRepositoryMock.getAllByRole(any(Role.class))).thenReturn(expectedEmployeeList);
        when(managerMapperMock.toEmployeeDto(expectedEmployeeList.get(0))).thenReturn(expectedEmployeeDtoList.get(0));
        List<EmployeeDto> result = employeeServiceTest.getAllByRole(Role.MANAGER);
        assertEquals(expectedEmployeeDtoList, result);
    }
    @Test
    public void testGetAllByRole_EmptyList() {
        Role role = Role.MANAGER;
        when(employeeRepositoryMock.getAllByRole(any(Role.class))).thenReturn(Collections.emptyList());
        assertThrows(EmptyEmployeeListException.class, () -> {
            employeeServiceTest.getAllByRole(role);
        });
        verify(employeeRepositoryMock).getAllByRole(role);
        verify(managerMapperMock, never()).toEmployeeDto(any());
    }
    @Test
    void testGetEmployeeByName() {
        when(employeeRepositoryMock.findEmployeeByName(anyString(),anyString())).thenReturn(expectedEmployee);
        when(managerMapperMock.toEmployeeDto(any())).thenReturn(expectedEmployeeDto);
        EmployeeDto result = employeeServiceTest.getEmployeeByName("Name", "FamiliaName");
        assertEquals(expectedEmployeeDto, result);
    }
    @Test
    void testGetEmployeeByName_WhenEmployeeNotExists() {
        when(employeeRepositoryMock.findEmployeeByName(anyString(),anyString())).thenReturn(null);
        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceTest.getEmployeeByName("Name", "FamiliaName"));
    }
    @Test
    public void testUpdateEmployee() {
        when(employeeRepositoryMock.findEmployeeByEmail(anyString())).thenReturn(expectedEmployee);
        employeeServiceTest.updateEmployee("NewName", "NewLastName", Role.MANAGER,"new@test.com",
                "+491234567890", Country.GERMANY, true);
        verify(employeeRepositoryMock).findEmployeeByEmail("new@test.com");
        verify(employeeRepositoryMock).updateEmployee("NewName", "NewLastName", Role.MANAGER,"new@test.com",
                "+491234567890", Country.GERMANY);
        verify(employeeRepositoryMock).save(any(Employee.class));
    }
    @Test
    public void testUpdateEmployee_ClientNotFound() {
        when(employeeRepositoryMock.findEmployeeByEmail(anyString())).thenReturn(null);
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeServiceTest.updateEmployee("NewName", "NewLastName", Role.MANAGER,"Non@Existent.EMail",
                    "+491234567890", Country.GERMANY, true);
        });
        verify(employeeRepositoryMock).findEmployeeByEmail("Non@Existent.EMail");
        verify(employeeRepositoryMock, never()).updateEmployee(anyString(), anyString(), any(Role.class), anyString(), anyString(), any(Country.class));
        verify(employeeRepositoryMock, never()).save(any(Employee.class));
    }
    @Test
    public void testCreateNewEmployee() {
        when(employeeRepositoryMock.findEmployeeByEmail(anyString())).thenReturn(null);
        when(managerMapperMock.toEmployee(any(EmployeeDto.class))).thenReturn(expectedEmployee);
        when(passwordEncoderMock.encode(anyString())).thenReturn("password");
        when(employeeRepositoryMock.save(any(Employee.class))).thenReturn(expectedEmployee);
        Employee result = employeeServiceTest.createNewEmployee(expectedEmployeeDto);
        assertEquals(expectedEmployee, result);
        verify(employeeRepositoryMock).findEmployeeByEmail(expectedEmployeeDto.getEmail());
        verify(managerMapperMock).toEmployee(expectedEmployeeDto);
        verify(passwordEncoderMock).encode(expectedEmployeeDto.getPassword());
        verify(employeeRepositoryMock).save(expectedEmployee);
    }
    @Test
    public void testCreateNewEmployee_EmailIsUsedException() {
        when(employeeRepositoryMock.findEmployeeByEmail(anyString())).thenReturn(expectedEmployee);
        assertThrows(EmailIsUsedException.class, () -> {
            employeeServiceTest.createNewEmployee(expectedEmployeeDto);
        });
        verify(employeeRepositoryMock).findEmployeeByEmail(expectedEmployeeDto.getEmail());
        verify(managerMapperMock, never()).toEmployee(any(EmployeeDto.class));
        verify(passwordEncoderMock, never()).encode(anyString());
        verify(employeeRepositoryMock, never()).save(any(Employee.class));
    }
}
