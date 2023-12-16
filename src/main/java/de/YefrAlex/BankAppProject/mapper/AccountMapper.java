package de.YefrAlex.BankAppProject.mapper;


import de.YefrAlex.BankAppProject.dto.AccountDto;
import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.Employee;
import org.mapstruct.*;

import java.util.UUID;


@Mapper(componentModel="spring")
public interface AccountMapper {

    @Mapping( source="mainManagerId", target = "mainManager")
    @Mapping( source="assistantManagerId", target = "assistantManager")
    AccountForClientDto toAccountForClientDto(Account account);

    @Mapping( source="clientId.taxCode", target = "taxCode")
    @Mapping( source="mainManagerId.email", target = "mainManager")
    @Mapping( source="assistantManagerId.email.", target = "assistantManager")
    AccountDto toAccountDto(Account account);
    Account toAccountEntity(AccountDto accountDto);

}
