package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.AgreementDto;
import de.YefrAlex.BankAppProject.entity.Agreement;

import java.math.BigDecimal;
import java.util.List;

public interface AgreementService {
    List<AgreementDto> findAll();
    Agreement saveAgreement(AgreementDto agreementDto);
    void updateAgreement(Long id, BigDecimal interestRate, BigDecimal amount, Integer duration, Boolean isBlocked);
}
