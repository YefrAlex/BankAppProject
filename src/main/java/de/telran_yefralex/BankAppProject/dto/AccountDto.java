package de.telran_yefralex.BankAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import lombok.*;


import java.math.BigDecimal;

@Getter
@Setter
public class AccountDto {

    private String taxCode;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String mainManager;
    private String assistantManager;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private CurrencyCode currencyCode;

    public AccountDto() {
    }

}
