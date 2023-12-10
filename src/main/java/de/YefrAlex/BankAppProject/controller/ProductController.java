package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.dto.ProductDto;
import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.enums.ProductType;
import de.YefrAlex.BankAppProject.service.ProductService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService=productService;
    }


    @GetMapping("/all-active")
    public ResponseEntity<List<ProductDto>> getAllActiveProductDto() {
        List<ProductDto> productsDto = productService.getAllActiveProductsDto();
        return ResponseEntity.ok(productsDto);
    }

    @GetMapping("/all-active/{type}")
    public ResponseEntity<List<ProductDto>> getAllActiveProductTypeDto(@PathVariable(name = "type") ProductType productType) {
        List<ProductDto> productsDto = productService.getActiveProductsWithType(productType);
        return ResponseEntity.ok(productsDto);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDto> getProductDtoById (@PathVariable(name = "id") Long id) {
        ProductDto productDto = productService.getProductDtoById(id);
        return ResponseEntity.ok(productDto);
    }

//    @GetMapping("/transaction/{id}")
//    public ResponseEntity<ProductDto> getAllTransaction (@PathVariable(name = "id") Long id) {
//        ProductDto productDto = productService.getProductDtoById(id);
//        return ResponseEntity.ok(productDto);
//    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "interestRate", required = false) BigDecimal interestRate,
            @RequestParam(name = "limit", required = false) BigDecimal limit,
            @RequestParam(name = "limitDuration", required = false) Integer limitDuration,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked){
        productService.updateProduct(id,  interestRate, limit,  limitDuration, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createNewProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createNewProduct(productDto);
        return ResponseEntity.created(URI.create("/" + product.getId())).body(product.getId());
    }


}
