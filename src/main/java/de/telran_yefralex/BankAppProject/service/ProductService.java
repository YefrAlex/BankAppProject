package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.ProductDto;
import de.telran_yefralex.BankAppProject.entity.Product;
import de.telran_yefralex.BankAppProject.entity.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    List<ProductDto> getAllActiveProductsDto();

    List<ProductDto> getActiveProductsWithType(ProductType productType);

    ProductDto getProductDtoById (Long id);

    void updateProduct(Long id, BigDecimal interestRate, BigDecimal limit, Integer limitDuration, Boolean isBlocked);

    Product createNewProduct(ProductDto productDto);
}
