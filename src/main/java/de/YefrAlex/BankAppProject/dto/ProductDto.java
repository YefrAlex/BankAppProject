package de.YefrAlex.BankAppProject.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private String productType;
    private String currency;
    private BigDecimal interestRate;
    private BigDecimal limit;
    private Integer limitDuration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
}
