package de.telran_yefralex.BankAppProject.controller.interfaces;

import de.telran_yefralex.BankAppProject.dto.ProductDto;
import de.telran_yefralex.BankAppProject.entity.enums.ProductType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@Tag(name = "product controller", description = "allows you to create, edit and receive information about product")
public interface ProductControllerInterface {

    @Operation(
            summary = "information about all active products",
            description = "allows you to get information about all active products, not require authorization"
    )
    ResponseEntity<List<ProductDto>> getAllActiveProductDto();

    @Operation(
            summary = "information about products with selected type",
            description = "allows you to get information about all products with selected type, not require authorization"
    )
    ResponseEntity<List<ProductDto>> getAllActiveProductTypeDto(@PathVariable(name = "type") ProductType productType);

    @Operation(
            summary = "information about product with selected id",
            description = "allows you to get information about product with selected id, requires the role of manager"
    )
    ResponseEntity<ProductDto> getProductDtoById(@PathVariable(name = "id") Long id);

    @Operation(
            summary = "update information about product",
            description = "allows you to update information about product, requires the role of manager"
    )
    ResponseEntity<String> updateProduct(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "interestRate", required = false) BigDecimal interestRate,
            @RequestParam(name = "limit", required = false) BigDecimal limit,
            @RequestParam(name = "limitDuration", required = false) Integer limitDuration,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked);

    @Operation(
            summary = "create a new product",
            description = "allows you to create a new product, requires the role of manager"
    )
    ResponseEntity<Long> createNewProduct(@RequestBody ProductDto productDto);
}
