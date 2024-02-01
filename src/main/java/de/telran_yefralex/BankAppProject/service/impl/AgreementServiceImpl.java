package de.telran_yefralex.BankAppProject.service.impl;

import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import de.telran_yefralex.BankAppProject.entity.Agreement;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.AccountNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.AgreementNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyAgreementsListException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.ProductNotFoundException;
import de.telran_yefralex.BankAppProject.mapper.AgreementMapper;
import de.telran_yefralex.BankAppProject.repository.AccountRepository;
import de.telran_yefralex.BankAppProject.repository.AgreementRepository;
import de.telran_yefralex.BankAppProject.repository.ProductRepository;
import de.telran_yefralex.BankAppProject.service.AgreementService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgreementServiceImpl implements AgreementService {

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
        List<Agreement> agreements = agreementRepository.findAll();
        if (agreements.isEmpty()){
            throw new EmptyAgreementsListException(ErrorMessage.EMPTY_AGREEMENTS_LIST);
        }
        return agreements.stream()
                .map(agreementMapper::toAgreementDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AgreementDto> findMyAgreement(String userEmail) {
        List<Agreement> agreements = agreementRepository.findMyAgreement(userEmail);
        if (agreements.isEmpty()){
            throw new EmptyAgreementsListException(ErrorMessage.EMPTY_AGREEMENTS_LIST);
        }
        return agreements.stream()
                .map(agreementMapper::toAgreementDto)
                .collect(Collectors.toList());
    }

    public Agreement saveAgreement(AgreementDto agreementDto) {
        Agreement agreement=new Agreement();
        agreement.setAccountId(accountRepository.getByNumber(agreementDto.getAccountNumber()).orElseThrow(() -> new AccountNotFoundException(String.format(ErrorMessage.ACCOUNT_NOT_FOUND, agreementDto.getAccountNumber()))));
        agreement.setProductId(productRepository.findById(agreementDto.getProductId()).orElseThrow(() -> new ProductNotFoundException(String.format(ErrorMessage.PRODUCT_NOT_FOUND, agreementDto.getProductId()))));
        agreement.setInterestRate(agreementDto.getInterestRate());
        agreement.setAmount(agreementDto.getAmount());
        agreement.setDuration(agreementDto.getDuration());
        agreement.setDescription(agreementDto.getDescription());
        agreementRepository.save(agreement);
        return agreement;
    }

    public void updateAgreement(Long id, BigDecimal interestRate, BigDecimal amount, Integer duration, Boolean isBlocked) {
        Agreement agreement = agreementRepository.findById(id).orElseThrow(() -> new AgreementNotFoundException(String.format(ErrorMessage.AGREEMENT_NOT_FOUND, id)));
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
