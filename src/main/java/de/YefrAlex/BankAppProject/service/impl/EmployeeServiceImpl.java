package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.EmployeeDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.Employee;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.entity.enums.Role;
import de.YefrAlex.BankAppProject.mapper.ManagerMapper;
import de.YefrAlex.BankAppProject.repository.EmployeeRepository;
import de.YefrAlex.BankAppProject.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ManagerMapper managerMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ManagerMapper managerMapper) {
        this.employeeRepository=employeeRepository;
        this.managerMapper=managerMapper;
    }

    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepository.findAll().stream().map(managerMapper::toEmployeeDto).toList();
    }

    @Override
    public List<EmployeeDto> getAllManagers() {
        return employeeRepository.findAll().stream().filter(emp -> "MANAGER".equals(emp.getRole().toString())).map(managerMapper::toEmployeeDto).toList();
    }

    @Override
    public List<EmployeeDto> getAllAdmins() {
        return employeeRepository.findAll().stream().filter(emp -> "ADMIN".equals(emp.getRole().toString())).map(managerMapper::toEmployeeDto).toList();
    }

    @Override
    public EmployeeDto getEmployeeByName(String firstName, String lastName) {
        return  employeeRepository.findAll().stream().filter(emp -> (firstName.equals(emp.getFirstName()) & lastName.equals(emp.getLastName())))
                .map(managerMapper::toEmployeeDto).toList().get(0);
    }

    @Override
    public void updateEmployee(String firstName, String lastName, Role role, String email, String phone, Country country, UUID id) {
         employeeRepository.updateEmployee(id,firstName, lastName, role, email, phone, country);
    }

    @Override
    public Employee createNewEmployee(EmployeeDto employeeDto) {
        Employee employee = managerMapper.toEmployee(employeeDto);
        employee.setId(null);
        return employeeRepository.save(employee);
    }
}
