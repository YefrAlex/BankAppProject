package de.telran_yefralex.BankAppProject.controller;


import de.telran_yefralex.BankAppProject.controller.interfaces.AgreementControllerInterface;
import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import de.telran_yefralex.BankAppProject.entity.Agreement;
import de.telran_yefralex.BankAppProject.service.AgreementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.security.Principal;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/agreement")
public class AgreementController implements AgreementControllerInterface {

    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService=agreementService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<List<AgreementDto>> getAll() {
        List<AgreementDto> allAgreements=agreementService.findAll();
        return ResponseEntity.ok(allAgreements);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myagreements")
    public ResponseEntity<List<AgreementDto>> getMyAgreements(Principal principal) {
        String userEmail = principal.getName();
        List<AgreementDto> allAgreements=agreementService.findMyAgreement(userEmail);
        return ResponseEntity.ok(allAgreements);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/newagreement")
    public ResponseEntity<HttpStatus> createNewAgreement (@RequestBody AgreementDto agreementDto)
            throws ServerException {
        Agreement agreement=agreementService.saveAgreement(agreementDto);
        if (agreement == null) {
            throw new ServerException("CreatedAgreement_Errror");
        } else {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAgreement(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "interestRate", required = false) BigDecimal interestRate,
            @RequestParam(name = "amount", required = false) BigDecimal amount,
            @RequestParam(name = "duration", required = false) Integer duration,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked) {
        agreementService.updateAgreement(id, interestRate, amount, duration, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
