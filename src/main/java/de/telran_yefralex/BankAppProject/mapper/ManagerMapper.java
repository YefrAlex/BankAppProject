package de.telran_yefralex.BankAppProject.mapper;

import de.telran_yefralex.BankAppProject.dto.EmployeeDto;
import de.telran_yefralex.BankAppProject.dto.ManagerForClientDto;
import de.telran_yefralex.BankAppProject.entity.Employee;
import org.mapstruct.*;

@Mapper(componentModel="spring")
public interface ManagerMapper {

    ManagerForClientDto toManagerForClientDto(Employee employee);
    EmployeeDto toEmployeeDto (Employee employee);
    Employee toEmployee (EmployeeDto employeeDto);
}
