package de.telran_yefralex.BankAppProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDto {
        private String debitAccountNumber;
        private String creditAccountNumber;
        private BigDecimal amount;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private CurrencyCode currencyCode;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String description;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private LocalDateTime createdAt;

        public TransactionDto() {
        }
}
