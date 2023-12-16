package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.dto.EmployeeDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.Employee;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.entity.enums.Role;
import de.YefrAlex.BankAppProject.service.impl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/find/all")
    public ResponseEntity<List<EmployeeDto>> findAllShort() {
        return ResponseEntity.ok(employeeService.getAll());
    }
    @GetMapping("/findrole/{role}")
    public ResponseEntity<List<EmployeeDto>> findAllByRole(@PathVariable(name = "role") Role role) {
        return ResponseEntity.ok(employeeService.getAllByRole(role));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployee(
            @PathVariable(name = "id") UUID id,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "role", required = false) Role role,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "country", required = false) Country country,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked){
        employeeService.updateEmployee(firstName, lastName, role, email, phone, country, id, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> createNewEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.createNewEmployee(employeeDto);
        return ResponseEntity.created(URI.create("/" + employee.getId())).body(employee.getId());
    }

}
