package de.YefrAlex.BankAppProject.entity.enums;

public enum TransactionType {
    TRANSFER,
    PAYMENT,
    WITHDRAW,
    DEPOSIT;

    @Override
    public String toString() {
        return "TransactionType{}";
    }
}
