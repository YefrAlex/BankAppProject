package de.telran_yefralex.BankAppProject.dto;


import de.telran_yefralex.BankAppProject.entity.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TransactionPlusDto {

    private UUID id;
    AccountDto debitAccountIdDto;
    AccountDto creditAccountIdDto;
    private TransactionType type;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
}
