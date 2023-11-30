package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService=employeeService;
    }
}
