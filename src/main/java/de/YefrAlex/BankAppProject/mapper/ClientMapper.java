package de.YefrAlex.BankAppProject.mapper;


import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel="spring")
public interface ClientMapper {



    @Mapping( target = "numberOfAccounts", expression = "java(client.getAccounts().size())")
    public ClientShortDto toClientShortDto (Client client);
    @Mapping( source = "accounts", target = " accounts")
    public ClientFullInfoDto toClientFullInfoDto (Client client);

    public Client toClient (ClientFullInfoDto clientFullInfoDto);
}
