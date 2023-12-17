package de.telran_yefralex.BankAppProject.mapper;

import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import de.telran_yefralex.BankAppProject.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface AgreementMapper {

    @Mapping( source="accountId.accountNumber", target = "accountNumber")
    @Mapping(source="productId.id", target="productId")
    AgreementDto toAgreementDto (Agreement agreement);
}
