package de.telran_yefralex.BankAppProject.mapper;

import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import org.mapstruct.*;

@Mapper(componentModel="spring")
public interface ClientMapper {
    @Mapping( target = "numberOfAccounts", expression = "java(client.getAccounts().size())")
    public ClientShortDto toClientShortDto (Client client);
    @Mapping( source = "accounts", target = " accounts")
    public ClientFullInfoDto toClientFullInfoDto (Client client);

    public Client toClient (ClientFullInfoDto clientFullInfoDto);
}
