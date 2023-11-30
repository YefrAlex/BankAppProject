package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository=productRepository;
    }
}
