package de.telran_yefralex.BankAppProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class AgreementDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String accountNumber;
    private Long productId;
    private BigDecimal interestRate;
    private BigDecimal amount;
    private Integer duration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isBlocked;
}
