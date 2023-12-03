package de.YefrAlex.BankAppProject.mapper;

import de.YefrAlex.BankAppProject.dto.ProductDto;
import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import de.YefrAlex.BankAppProject.entity.enums.ProductType;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "productType", target = "productType", qualifiedByName = "enumGetName")
    @Mapping(source = "currencyCode", target = "currency", qualifiedByName = "enumGetCurrency")
    ProductDto toProductDto (Product product);

    @Named("enumGetName")
    default String enumGetName(ProductType productType) {
        return productType.getName();
    }

    @Named("enumGetCurrency")
    default String enumGetCurrency(CurrencyCode currencyCode) {
        return currencyCode.getCurrencyName();
    }
}
