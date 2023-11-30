package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository=employeeRepository;
    }
}
