package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.repository.AgreementRepository;
import org.springframework.stereotype.Service;

@Service
public class AgreementServiceImpl {

    private final AgreementRepository agreementRepository;


    public AgreementServiceImpl(AgreementRepository agreementRepository) {
        this.agreementRepository=agreementRepository;
    }
}
