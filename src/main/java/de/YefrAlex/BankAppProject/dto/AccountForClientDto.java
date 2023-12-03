package de.YefrAlex.BankAppProject.dto;


import lombok.*;
import java.math.BigDecimal;
@Getter
@Setter
public class AccountForClientDto {
    ManagerForClientDto mainManager;
    ManagerForClientDto assistantManager;
    String accountNumber;
    String type;
    BigDecimal balance;
    String currencyCode;

    public AccountForClientDto() {
    }


}
