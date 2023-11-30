package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository=transactionRepository;
    }
}
