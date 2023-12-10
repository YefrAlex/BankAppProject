package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.impl.AgreementServiceImpl;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/agreement")
public class AgreementController {

    private final AgreementServiceImpl agreementService;

    public AgreementController(AgreementServiceImpl agreementService) {
        this.agreementService=agreementService;
    }
}
