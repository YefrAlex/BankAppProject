package de.YefrAlex.BankAppProject.mapper;

import de.YefrAlex.BankAppProject.dto.AccountDto;
import de.YefrAlex.BankAppProject.dto.AccountForClientDto;
import de.YefrAlex.BankAppProject.entity.Account;
import org.mapstruct.*;


@Mapper(componentModel="spring")
public interface AccountMapper {

    @Mapping( source="mainManagerId", target = "mainManager")
    @Mapping( source="assistantManagerId", target = "assistantManager")
    AccountForClientDto toAccountForClientDto(Account account);

    Account toAccount (AccountDto accountDto);

    AccountDto toAccountDto (Account account);
}
