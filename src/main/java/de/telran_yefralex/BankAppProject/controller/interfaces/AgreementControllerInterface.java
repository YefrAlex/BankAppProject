package de.telran_yefralex.BankAppProject.controller.interfaces;

import de.telran_yefralex.BankAppProject.dto.AgreementDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.rmi.ServerException;
import java.security.Principal;
import java.util.List;

@Tag(name = "agreements controller", description = "allows you to create, edit and receive agreements information")
public interface AgreementControllerInterface {
    @Operation(
            summary = "all agreements information ",
            description = "allows you to get information about all agreements, requires the role of manager"
    )
    ResponseEntity<List<AgreementDto>> getAll();

    @Operation(
            summary = "all client's agreements information ",
            description = "allows the client to get information about all his contracts, requires the role of user"
    )
    ResponseEntity<List<AgreementDto>> getMyAgreements(Principal principal);
    @Operation(
            summary = "create a new agreement",
            description = "allows you to create a new agreement, requires the role of manager"
    )
    ResponseEntity<HttpStatus> createNewAgreement(@RequestBody AgreementDto agreementDto)
            throws ServerException;
    @Operation(
            summary = "update agreement",
            description = "allows you to update agreement, requires the role of manager"
    )
    ResponseEntity<String> updateAgreement(
            @PathVariable(name = "id") Long id,
            @RequestParam(name = "interestRate", required = false) BigDecimal interestRate,
            @RequestParam(name = "amount", required = false) BigDecimal amount,
            @RequestParam(name = "duration", required = false) Integer duration,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked);
}
