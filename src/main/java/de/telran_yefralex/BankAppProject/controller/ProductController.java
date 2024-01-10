package de.telran_yefralex.BankAppProject.controller;

import de.telran_yefralex.BankAppProject.controller.interfaces.ProductControllerInterface;
import de.telran_yefralex.BankAppProject.dto.ProductDto;
import de.telran_yefralex.BankAppProject.entity.Product;
import de.telran_yefralex.BankAppProject.entity.enums.ProductType;
import de.telran_yefralex.BankAppProject.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/product")
public class ProductController implements ProductControllerInterface {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService=productService;
    }

    @GetMapping("/all-active")
    public ResponseEntity<List<ProductDto>> getAllActiveProductDto(HttpServletRequest request) {
        List<ProductDto> productsDto=productService.getAllActiveProductsDto();
        log.info("getAllActiveProductDto was called from id = " + request.getRemoteAddr());
        return ResponseEntity.ok(productsDto);
    }

    @GetMapping("/all-active/{type}")
    public ResponseEntity<List<ProductDto>> getAllActiveProductTypeDto(@PathVariable(name = "type") ProductType productType, HttpServletRequest request) {
        log.info("getAllActiveProductType was called from id = " + request.getRemoteAddr());
        List<ProductDto> productsDto=productService.getActiveProductsWithType(productType);
        return ResponseEntity.ok(productsDto);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/id/{id}")
    public ResponseEntity<ProductDto> getProductDtoById(@PathVariable(name = "id") Long id, Principal principal, HttpServletRequest request) {
        log.info("product with id = " + id + " updated by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        ProductDto productDto=productService.getProductDtoById(id);
        return ResponseEntity.ok(productDto);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "interestRate", required = false) BigDecimal interestRate,
            @RequestParam(name = "limit", required = false) BigDecimal limit,
            @RequestParam(name = "limitDuration", required = false) Integer limitDuration,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked,
            Principal principal, HttpServletRequest request) {
        log.info("product with id = " + id + " updated by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        productService.updateProduct(id, interestRate, limit, limitDuration, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<Long> createNewProduct(@RequestBody ProductDto productDto, Principal principal, HttpServletRequest request) {
        Product product=productService.createNewProduct(productDto);
        log.info("product with id = " + product.getId() + " createded by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.created(URI.create("/" + product.getId())).body(product.getId());
    }
}
