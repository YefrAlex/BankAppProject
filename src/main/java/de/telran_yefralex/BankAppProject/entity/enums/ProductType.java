package de.telran_yefralex.BankAppProject.entity.enums;

import lombok.Getter;

@Getter
public enum ProductType {

    LOAN("cash loan"),
    AUTO_LOAN("car loan"),
    MORTGAGE("hypothec"),
    DEPOSIT("term deposit"),
    DEBIT_CARD ("Debit card"),
    CREDIT_CARD("Credit card");

    private final String name;

    ProductType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
