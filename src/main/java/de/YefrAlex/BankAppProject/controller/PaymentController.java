package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService=paymentService;
    }
}
