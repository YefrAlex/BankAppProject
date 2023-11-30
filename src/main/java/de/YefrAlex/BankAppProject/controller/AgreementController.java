package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.AgreementService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/agreement")
public class AgreementController {

    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService=agreementService;
    }
}
