package de.telran_yefralex.BankAppProject.service.impl;

import de.telran_yefralex.BankAppProject.dto.EmployeeDto;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmailIsUsedException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmployeeNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyEmployeeListException;
import de.telran_yefralex.BankAppProject.mapper.ManagerMapper;
import de.telran_yefralex.BankAppProject.repository.EmployeeRepository;
import de.telran_yefralex.BankAppProject.service.EmployeeService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ManagerMapper managerMapper;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ManagerMapper managerMapper, PasswordEncoder passwordEncoder) {
        this.employeeRepository=employeeRepository;
        this.managerMapper=managerMapper;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()){
            throw new EmptyEmployeeListException(ErrorMessage.EMPTY_EMPLOYEES_LIST);
        }
        return employees.stream().map(managerMapper::toEmployeeDto).toList();
    }

    @Override
    public List<EmployeeDto> getAllByRole(Role role) {
        List<Employee> employees = employeeRepository.getAllByRole(role);
        if (employees.isEmpty()){
            throw new EmptyEmployeeListException(ErrorMessage.EMPTY_EMPLOYEES_LIST);
        }
        return employees.stream().map(managerMapper::toEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeByName(String firstName, String lastName) {
        Employee employee = employeeRepository.findEmployeeByName(firstName,lastName);
        if (employee == null){
            throw new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND, firstName + " " + lastName);
        }
        return  managerMapper.toEmployeeDto(employee);
    }

    @Override
    public void updateEmployee(String firstName, String lastName, Role role, String email, String phone, Country country, Boolean isBlocked) {
        Employee employee = employeeRepository.findEmployeeByEmail(email);
        if (employee == null){
            throw new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND, email);
        }
        if (isBlocked != null){
            employee.setBlocked(isBlocked);
            employeeRepository.save(employee);
        }
         employeeRepository.updateEmployee(firstName, lastName, role, email, phone, country);
    }

    @Override
    public Employee createNewEmployee(EmployeeDto employeeDto) {
        if (employeeRepository.findEmployeeByEmail(employeeDto.getEmail())!= null){
            throw new EmailIsUsedException(ErrorMessage.IS_USED, employeeDto.getEmail());
        }
        Employee employee = managerMapper.toEmployee(employeeDto);
        employee.setId(null);
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        return employeeRepository.save(employee);
    }
}
