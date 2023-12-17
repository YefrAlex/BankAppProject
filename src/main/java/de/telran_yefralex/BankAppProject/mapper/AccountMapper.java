package de.telran_yefralex.BankAppProject.mapper;


import de.telran_yefralex.BankAppProject.dto.AccountDto;
import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import org.mapstruct.*;


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
