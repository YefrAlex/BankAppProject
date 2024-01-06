package de.telran_yefralex.BankAppProject.service;

import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import de.telran_yefralex.BankAppProject.entity.Agreement;

import java.math.BigDecimal;
import java.util.List;

public interface AgreementService {
    List<AgreementDto> findAll();
    List<AgreementDto> findMyAgreement(String userEmail);
    Agreement saveAgreement(AgreementDto agreementDto);
    void updateAgreement(Long id, BigDecimal interestRate, BigDecimal amount, Integer duration, Boolean isBlocked);


}
