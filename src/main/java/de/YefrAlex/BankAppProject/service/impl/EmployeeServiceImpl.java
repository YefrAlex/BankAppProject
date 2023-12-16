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
import java.util.stream.Collectors;

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
    public List<EmployeeDto> getAllByRole(Role role) {
        return employeeRepository.getAllByRole(role).stream().map(managerMapper::toEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto getEmployeeByName(String firstName, String lastName) {
        return  managerMapper.toEmployeeDto(employeeRepository.findEmployeeByName(firstName,lastName));
    }

    @Override
    public void updateEmployee(String firstName, String lastName, Role role, String email, String phone, Country country, UUID id, Boolean isBlocked) {
        if (isBlocked != null){
            Employee employee = employeeRepository.findById(id).get();
            employee.setBlocked(isBlocked);
            employeeRepository.save(employee);
        }
         employeeRepository.updateEmployee(id,firstName, lastName, role, email, phone, country);
    }

    @Override
    public Employee createNewEmployee(EmployeeDto employeeDto) {
        Employee employee = managerMapper.toEmployee(employeeDto);
        employee.setId(null);
        return employeeRepository.save(employee);
    }
}
