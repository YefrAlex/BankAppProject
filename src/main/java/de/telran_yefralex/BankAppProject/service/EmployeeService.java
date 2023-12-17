package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.EmployeeDto;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.Role;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeDto> getAll();
    List<EmployeeDto> getAllByRole(Role role);
    EmployeeDto getEmployeeByName(String firstName, String LastName);
    void updateEmployee(String firstName, String lastName, Role role, String email, String phone, Country country, UUID id, Boolean isBlocked);
    Employee createNewEmployee (EmployeeDto employeeDto);

}
