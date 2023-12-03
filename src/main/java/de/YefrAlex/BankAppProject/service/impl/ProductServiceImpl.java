package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.ProductDto;
import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.enums.ProductType;
import de.YefrAlex.BankAppProject.mapper.ProductMapper;
import de.YefrAlex.BankAppProject.repository.ProductRepository;
import de.YefrAlex.BankAppProject.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository=productRepository;
        this.productMapper=productMapper;
    }

    @Transactional
    @Override
    public List<ProductDto> getAllActiveProductsDto() {
        List<Product> allActiveProducts = productRepository.findAllActiveProducts();
        return allActiveProducts.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ProductDto> getActiveProductsWithType(ProductType type) {
        List<Product> allActiveProductsWithType = productRepository.findActiveProductsWithType(type);
        return allActiveProductsWithType.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductDtoById(Long id) {
        return productMapper.toProductDto(productRepository.getProductDtoById(id));
    }
}
