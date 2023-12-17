package de.telran_yefralex.BankAppProject.controller;

import de.telran_yefralex.BankAppProject.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService=paymentService;
    }
}
