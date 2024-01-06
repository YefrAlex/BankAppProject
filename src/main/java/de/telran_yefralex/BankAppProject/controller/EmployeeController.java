package de.telran_yefralex.BankAppProject.controller;

import de.telran_yefralex.BankAppProject.dto.EmployeeDto;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import de.telran_yefralex.BankAppProject.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;


    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService=employeeService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/find/all")
    public ResponseEntity<List<EmployeeDto>> findAllShort() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/findrole/{role}")
    public ResponseEntity<List<EmployeeDto>> findAllByRole(@PathVariable(name = "role") Role role) {
        return ResponseEntity.ok(employeeService.getAllByRole(role));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable(name = "email") String email,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "role", required = false) Role role,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "country", required = false) Country country,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked) {
        employeeService.updateEmployee(firstName, lastName, role, email, phone, country, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UUID> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee=employeeService.createNewEmployee(employeeDto);
        return ResponseEntity.created(URI.create("/" + employee.getId())).body(employee.getId());
    }

}
