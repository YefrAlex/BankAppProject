package de.telran_yefralex.BankAppProject.controller.interfaces;

import de.telran_yefralex.BankAppProject.dto.AccountDto;
import de.telran_yefralex.BankAppProject.dto.AccountForClientDto;
import de.telran_yefralex.BankAppProject.entity.Account;
import de.telran_yefralex.BankAppProject.entity.enums.AccountType;
import de.telran_yefralex.BankAppProject.entity.enums.CurrencyCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.rmi.ServerException;
import java.security.Principal;
import java.util.List;

@Tag(name = "accounts controller", description = "allows you to create, edit and receive account information")
public interface AccountControllerInterface {

    @Operation(
            summary = "all accounts information ",
            description = "allows you to get information about all accounts, requires the role of admin"
    )
    ResponseEntity<List<AccountDto>> getAll(Principal principal, HttpServletRequest request);

    @Operation(
            summary = "all manager's accounts information ",
            description = "allows you to get information about manager's accounts, requires the role of manager"
    )
    ResponseEntity<List<AccountDto>> getManagerAllAccount(Principal principal, HttpServletRequest request);

    @Operation(
            summary = "all client's accounts information ",
            description = "allows you to get information about client's accounts, requires the role of user"
    )
    ResponseEntity<List<AccountForClientDto>> getAllClientsAccount(Principal principal, HttpServletRequest request);

    @Operation(
            summary = "account information by account number",
            description = "allows you to get information about account by account number, requires the role of manager"
    )
    ResponseEntity<AccountDto> getAccountByNumber(@PathVariable(name = "number") @Parameter(description = "enter account number")
                                                  String accountNumber, Principal principal, HttpServletRequest request);

    @Operation(
            summary = "create a new account",
            description = "allows you to create a new account, requires the role of manager"
    )
    ResponseEntity<HttpStatus> createNewAccount(@RequestBody AccountDto accountDto, Principal principal, HttpServletRequest request)
            throws ServerException;

    @Operation(
            summary = "update account",
            description = "allows you to update account, requires the role of manager"
    )
    ResponseEntity<String> updateAccount(
            @PathVariable(name = "number") String accountNumber,
            @RequestParam(name = "mainManagerEmail", required = false) String mainManagerEmail,
            @RequestParam(name = "assistantManagerEmail", required = false) String assistantManagerEmail,
            @RequestParam(name = "type", required = false) AccountType type,
            @RequestParam(name = "currencyCode", required = false) CurrencyCode currencyCode,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked,
            Principal principal, HttpServletRequest request);
}
