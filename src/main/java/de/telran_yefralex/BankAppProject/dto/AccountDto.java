package de.telran_yefralex.BankAppProject.dto;

import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import lombok.*;


import java.math.BigDecimal;

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
