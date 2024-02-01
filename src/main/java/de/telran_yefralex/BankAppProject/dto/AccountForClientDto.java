package de.telran_yefralex.BankAppProject.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.math.BigDecimal;
@Getter
@Setter
public class AccountForClientDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    ManagerForClientDto mainManager;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    ManagerForClientDto assistantManager;
    String accountNumber;
    String type;
    BigDecimal balance;
    String currencyCode;
}
