package de.telran_yefralex.BankAppProject.entity.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CurrencyCode {
    PLN("Zloty", 4.58),
    USD("US Dollar", 1.13),
    EUR("Euro", 1.0),
    GBP("Pound Sterling", 0.85),
    CHF("Swiss Franc", 1.07),
    HUF("Forint", 358.80),
    UAH("Hryvnia", 30.20),
    CZK("Czech Koruna", 25.69),
    DKK("Danish Krone", 7.44),
    NOK("Norwegian Krone", 9.96),
    SEK("Swedish Krona", 10.36),
    CNY("Yuan Renminbi", 7.77),
    JPY("Yen", 132.36),
    ISK("Iceland Krona", 155.90),
    ILS("New Israeli Sheqel", 3.91),
    TRY("Turkish Lira", 10.17);

    private final String currencyName;
    private double exchangeRateToEuro;

    CurrencyCode(String currencyName, double exchangeRateToEuro) {
        this.currencyName = currencyName;
        this.exchangeRateToEuro = exchangeRateToEuro;
    }

    public void setExchangeRateToEuro(double exchangeRateToEuro) {
        this.exchangeRateToEuro = exchangeRateToEuro;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
