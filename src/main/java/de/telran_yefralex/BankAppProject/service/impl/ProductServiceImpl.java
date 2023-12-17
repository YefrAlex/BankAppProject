package de.telran_yefralex.BankAppProject.service.impl;

import de.telran_yefralex.BankAppProject.dto.ProductDto;
import de.telran_yefralex.BankAppProject.entity.Product;
import de.telran_yefralex.BankAppProject.entity.enums.ProductType;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyProductsListException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.ProductNotFoundException;
import de.telran_yefralex.BankAppProject.mapper.ProductMapper;
import de.telran_yefralex.BankAppProject.repository.ProductRepository;
import de.telran_yefralex.BankAppProject.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        if (allActiveProducts.isEmpty()){
            throw new EmptyProductsListException(ErrorMessage.EMPTY_PRODUCTS_LIST);
        }
        return allActiveProducts.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ProductDto> getActiveProductsWithType(ProductType type) {
        List<Product> allActiveProductsWithType = productRepository.findActiveProductsWithType(type);
        if (allActiveProductsWithType.isEmpty()){
            throw new EmptyProductsListException(ErrorMessage.EMPTY_PRODUCTS_LIST);
        }
        return allActiveProductsWithType.stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductDtoById(Long id) {
        Product product = productRepository.getProductDtoById(id);
        if (product == null){
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
        return productMapper.toProductDto(product);
    }

    @Transactional
    @Override
    public void updateProduct(Long id, BigDecimal interestRate, BigDecimal limit, Integer limitDuration, Boolean isBlocked) {
        Product product = productRepository.getProductDtoById(id);
        if (product == null){
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
        productRepository.updateProduct(id, interestRate, limit, limitDuration, isBlocked);
    }

    @Transactional
    @Override
    public Product createNewProduct(ProductDto productDto) {
        Product product = productMapper.toProduct(productDto);
            product.setId(null);
            return productRepository.save(product);
    }
}
