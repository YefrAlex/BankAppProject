package de.YefrAlex.BankAppProject.dto;

import de.YefrAlex.BankAppProject.entity.enums.AccountType;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import lombok.*;


import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AccountDto {
    private String taxCode;
    private String mainManager;
    private String assistantManager;
    private String accountNumber;
    private AccountType type;
    private BigDecimal balance;
    private CurrencyCode currencyCode;

    public AccountDto() {
    }

    //    @OneToMany( mappedBy = "debitAccountId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Transaction> debitTransactions = new HashSet<>();
//    @OneToMany( mappedBy = "debitAccountId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Transaction> creditTransactions = new HashSet<>();
}
