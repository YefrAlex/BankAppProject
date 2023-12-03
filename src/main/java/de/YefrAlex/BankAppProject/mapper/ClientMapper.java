package de.YefrAlex.BankAppProject.mapper;


import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.entity.Client;
import org.mapstruct.*;




@Mapper(componentModel="spring")
public interface ClientMapper {


    //@Mapping(source = "client.numberOfAccounts", target = "accounts")
    @Mapping( target = "numberOfAccounts", expression = "java(client.getAccounts().size())")
    ClientShortDto toClientShortDto (Client client);


    ClientFullInfoDto toClientFullInfoDto (Client client);



}
