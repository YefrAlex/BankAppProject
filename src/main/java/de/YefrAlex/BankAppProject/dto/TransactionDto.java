package de.YefrAlex.BankAppProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.YefrAlex.BankAppProject.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class TransactionDto {

        private String debitAccountNumber;
        private String creditAccountNumber;
        private BigDecimal amount;
        private TransactionType type;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String description;
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private Date createdAt;

}