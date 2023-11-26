package de.YefrAlex.BankAppProject.entity.enums;

public enum ProductType {
    LOAN("Loan"),
    DEPOSIT("Deposit"),
    DEBIT_CARD("Debit card"),
    CREDIT_CARD("Credit card");

    private final String name;

    ProductType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
