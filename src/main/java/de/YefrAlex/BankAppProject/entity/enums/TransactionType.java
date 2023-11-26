package de.YefrAlex.BankAppProject.entity.enums;

public enum TransactionType {
    TRANSFER("Transfer"),
    PAYMENT("Payment"),
    WITHDRAW("Withdraw"),
    DEPOSIT("Deposit");

    private final String name;

    TransactionType(String name) {
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
