package de.YefrAlex.BankAppProject.mapper;

import de.YefrAlex.BankAppProject.dto.ManagerForClientDto;
import de.YefrAlex.BankAppProject.entity.Employee;
import org.mapstruct.*;


@Mapper(componentModel="spring")
public interface ManagerMapper {
    //@Mapping(target = "fullName", expression = "java(employee.getFirstName() + ' ' + employee.getLastName())")

//    @Mapping(source = "employee", target = "fullName", qualifiedByName = "getManagerFullName")
    ManagerForClientDto toManagerForClientDto(Employee employee);


//    @Named("getManagerFullName")
//    default String getManagerFullName(Employee employee) {
//        return String.format("%s %s", employee.getFirstName(), employee.getLastName());
//    }

}
