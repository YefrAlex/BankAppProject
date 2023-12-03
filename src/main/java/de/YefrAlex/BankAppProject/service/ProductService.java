package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.ProductDto;
import de.YefrAlex.BankAppProject.entity.enums.ProductType;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllActiveProductsDto();

    List<ProductDto> getActiveProductsWithType(ProductType productType);

    ProductDto getProductDtoById (Long id);
}
