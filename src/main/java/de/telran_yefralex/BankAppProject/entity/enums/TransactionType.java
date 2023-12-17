package de.telran_yefralex.BankAppProject.entity.enums;

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
