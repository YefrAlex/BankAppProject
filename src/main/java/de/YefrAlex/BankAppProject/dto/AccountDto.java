package de.YefrAlex.BankAppProject.dto;


import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.Employee;
import de.YefrAlex.BankAppProject.entity.enums.AccountType;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class AccountDto {
    UUID id;
    Client clientId;
    Employee mainManagerId;
    Employee assistantManagerId;
    String accountNumber;
    AccountType type;
    BigDecimal balance;
    CurrencyCode currencyCode;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean isBlocked;
}
