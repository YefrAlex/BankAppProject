package de.telran_yefralex.BankAppProject.controller;

import de.telran_yefralex.BankAppProject.controller.interfaces.EmployeeControllerInterface;
import de.telran_yefralex.BankAppProject.dto.EmployeeDto;
import de.telran_yefralex.BankAppProject.entity.Employee;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import de.telran_yefralex.BankAppProject.service.impl.EmployeeServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController implements EmployeeControllerInterface {

    private final EmployeeServiceImpl employeeService;


    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService=employeeService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/find/all")
    public ResponseEntity<List<EmployeeDto>> findAllShort(Principal principal, HttpServletRequest request) {
        log.info("findAllShort employee was called by employee " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(employeeService.getAll());
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/findrole/{role}")
    public ResponseEntity<List<EmployeeDto>> findAllByRole(@PathVariable(name = "role") Role role, Principal principal, HttpServletRequest request) {
        log.info("findAllByRole employee was called by employee " + principal.getName() + " from ip " + request.getRemoteAddr());
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
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked,
            Principal principal, HttpServletRequest request) {
        log.info("employee with email = " + email + " updated by admin " + principal.getName() + " from ip " + request.getRemoteAddr());
        employeeService.updateEmployee(firstName, lastName, role, email, phone, country, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<UUID> createNewEmployee(@RequestBody EmployeeDto employeeDto, Principal principal, HttpServletRequest request) {
        Employee employee=employeeService.createNewEmployee(employeeDto);
        log.info("employee with id = " + employee.getId() + " created by admin " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.created(URI.create("/" + employee.getId())).body(employee.getId());
    }

}
