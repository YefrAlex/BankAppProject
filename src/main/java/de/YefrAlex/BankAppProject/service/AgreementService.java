package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.repository.AgreementRepository;
import org.springframework.stereotype.Service;

@Service
public class AgreementService {

    private final AgreementRepository agreementRepository;


    public AgreementService(AgreementRepository agreementRepository) {
        this.agreementRepository=agreementRepository;
    }
}
