package de.YefrAlex.BankAppProject.mapper;

import de.YefrAlex.BankAppProject.dto.EmployeeDto;
import de.YefrAlex.BankAppProject.dto.ManagerForClientDto;
import de.YefrAlex.BankAppProject.entity.Employee;
import org.mapstruct.*;


@Mapper(componentModel="spring")
public interface ManagerMapper {

    ManagerForClientDto toManagerForClientDto(Employee employee);
    EmployeeDto toEmployeeDto (Employee employee);
    Employee toEmployee (EmployeeDto employeeDto);

}
