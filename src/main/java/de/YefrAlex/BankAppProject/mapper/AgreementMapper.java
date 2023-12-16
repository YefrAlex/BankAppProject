package de.YefrAlex.BankAppProject.mapper;

import de.YefrAlex.BankAppProject.dto.AgreementDto;
import de.YefrAlex.BankAppProject.entity.Agreement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface AgreementMapper {

    @Mapping( source="accountId.accountNumber", target = "accountNumber")
    @Mapping(source="productId.id", target="productId")
    AgreementDto toAgreementDto (Agreement agreement);
}
