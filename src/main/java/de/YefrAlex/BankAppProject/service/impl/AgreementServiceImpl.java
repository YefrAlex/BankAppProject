package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.dto.AgreementDto;
import de.YefrAlex.BankAppProject.entity.Agreement;
import de.YefrAlex.BankAppProject.mapper.AgreementMapper;
import de.YefrAlex.BankAppProject.repository.AccountRepository;
import de.YefrAlex.BankAppProject.repository.AgreementRepository;
import de.YefrAlex.BankAppProject.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgreementServiceImpl {

    private final AgreementRepository agreementRepository;
    private final AgreementMapper agreementMapper;
    private final AccountRepository accountRepository;
    private final ProductRepository productRepository;


    public AgreementServiceImpl(AgreementRepository agreementRepository, AgreementMapper agreementMapper, AccountRepository accountRepository, ProductRepository productRepository) {
        this.agreementRepository=agreementRepository;
        this.agreementMapper=agreementMapper;
        this.accountRepository=accountRepository;
        this.productRepository=productRepository;
    }

    public List<AgreementDto> findAll() {

        return agreementRepository.findAll().stream()
                .map(agreementMapper::toAgreementDto)
                .collect(Collectors.toList());
    }

    public Agreement saveAgreement(AgreementDto agreementDto) {
        Agreement agreement=new Agreement();
        agreement.setAccountId(accountRepository.getByNumber(agreementDto.getAccountNumber()).get());
        agreement.setProductId(productRepository.findById(agreementDto.getProductId()).get());
        agreement.setInterestRate(agreementDto.getInterestRate());
        agreement.setAmount(agreementDto.getAmount());
        agreement.setDuration(agreementDto.getDuration());
        agreement.setDescription(agreementDto.getDescription());
        agreementRepository.save(agreement);
        return agreement;
    }

    public void updateAgreement(Long id, BigDecimal interestRate, BigDecimal amount, Integer duration, Boolean isBlocked) {
        Agreement agreement = agreementRepository.findById(id).get();
        if (interestRate != null){
            agreement.setInterestRate(interestRate);
        }
        if (amount != null){
            agreement.setAmount(amount);
        }
        if (duration != null) {
            agreement.setDuration(duration);
        }
        if (isBlocked != null){
            agreement.setIsBlocked(isBlocked);
        }
        agreement.setUpdateAt(LocalDateTime.now());
        agreementRepository.save(agreement);
    }
}
