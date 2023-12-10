package de.YefrAlex.BankAppProject.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.math.BigDecimal;
@Getter
@Setter
public class AccountForClientDto {
    ManagerForClientDto mainManager;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    ManagerForClientDto assistantManager;
    String accountNumber;
    String type;
    BigDecimal balance;
    String currencyCode;

    public AccountForClientDto() {
    }

}
