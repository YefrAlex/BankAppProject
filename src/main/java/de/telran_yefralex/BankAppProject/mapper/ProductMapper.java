package de.telran_yefralex.BankAppProject.mapper;

import de.telran_yefralex.BankAppProject.dto.ProductDto;
import de.telran_yefralex.BankAppProject.entity.Product;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import de.telran_yefralex.BankAppProject.entity.enums.ProductType;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    List<ProductType> productTypes = List.of(ProductType.values());
    List<CurrencyCode> currencyCodes = List.of(CurrencyCode.values());

    @Mapping(source = "productType", target = "productType", qualifiedByName = "enumGetName")
    @Mapping(source = "currencyCode", target = "currency", qualifiedByName = "enumGetCurrency")
    ProductDto toProductDto (Product product);

    @Mapping(source = "productType", target = "productType", qualifiedByName = "enumSetType")
    @Mapping(source = "currency", target = "currencyCode", qualifiedByName = "enumSetCurrency")
    Product toProduct (ProductDto productDto);

    @Named("enumGetName")
    default String enumGetName(ProductType productType) {
        return productType.getName();
    }

    @Named("enumGetCurrency")
    default String enumGetCurrency(CurrencyCode currencyCode) {
        return currencyCode.getCurrencyName();
    }

    @Named("enumSetType")
    default ProductType enumSetType(String productType) {
        ProductType prod = null;
        for (int i=0; i < productTypes.size(); i++) {
            if (productTypes.get(i).getName().equals(productType)){
                prod = productTypes.get(i);
                break;
            }
        }
        return prod;
    }

    @Named("enumSetCurrency")
    default CurrencyCode enumSetCurrency(String currency) {
        CurrencyCode curCod = null;
        for (int i=0; i < currencyCodes.size(); i++) {
            if (currencyCodes.get(i).getCurrencyName().equals(currency)){
                curCod = currencyCodes.get(i);
                break;
            }
        }
        return curCod;
    }
}
