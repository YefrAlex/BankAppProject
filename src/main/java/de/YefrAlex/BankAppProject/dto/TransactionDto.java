package de.YefrAlex.BankAppProject.dto;

import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class TransactionDto {

        private String debitAccountNumber;
        private String creditAccountNumber;
        private BigDecimal amount;
        private String type;
        private String description;
        private Date createdAt;

}
