package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService=productService;
    }
}
