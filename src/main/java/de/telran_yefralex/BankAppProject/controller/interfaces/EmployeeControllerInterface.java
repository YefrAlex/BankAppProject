package de.telran_yefralex.BankAppProject.controller.interfaces;

import de.telran_yefralex.BankAppProject.dto.EmployeeDto;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Tag(name = "employee controller", description = "allows you to create, edit and receive information about employee")
public interface EmployeeControllerInterface {
    @Operation(
            summary = "short information about all employees",
            description = "allows you to get information about all employees, requires the role of manager or admin"
    )
    ResponseEntity<List<EmployeeDto>> findAllShort(Principal principal, HttpServletRequest request);

    @Operation(
            summary = "information about all employees with the selected role",
            description = "allows you to get information about all employees with the selected role, requires the role of manager or admin"
    )
    ResponseEntity<List<EmployeeDto>> findAllByRole(@PathVariable(name = "role") Role role, Principal principal, HttpServletRequest request);

    @Operation(
            summary = "update information about employee",
            description = "allows you to update information about employee, requires the role of admin"
    )
    ResponseEntity<String> updateEmployee(
            @PathVariable(name = "email") String email,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "role", required = false) Role role,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "country", required = false) Country country,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked,
            Principal principal, HttpServletRequest request);

    @Operation(
            summary = "create a new employee",
            description = "allows you to create a new employee, requires the role of admin"
    )
    ResponseEntity<UUID> createNewEmployee(@RequestBody EmployeeDto employeeDto, Principal principal, HttpServletRequest request);
}
