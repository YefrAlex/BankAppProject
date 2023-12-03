package de.YefrAlex.BankAppProject.dto;


import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto {
    private String productType;
    private BigDecimal interestRate;
    private BigDecimal limit;
    private String currency;
    private Integer limitDuration;
}
