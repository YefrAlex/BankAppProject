package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.repository.PaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository=paymentRepository;
    }
}
