package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository=transactionRepository;
    }
}
