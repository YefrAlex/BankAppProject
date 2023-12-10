package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.ProductDto;
import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductDto> getAllActiveProductsDto();

    List<ProductDto> getActiveProductsWithType(ProductType productType);

    ProductDto getProductDtoById (Long id);

    void updateProduct(Long id, BigDecimal interestRate, BigDecimal limit, Integer limitDuration, Boolean isBlocked);

    Product createNewProduct(ProductDto productDto);
}
