package de.telran_yefralex.BankAppProject.controller.interfaces;

import de.telran_yefralex.BankAppProject.dto.TransactionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Tag(name = "transaction controller", description = "allows you to create transaction, and receive information about them")
public interface TransactionControllerInterface {

    @Operation(
            summary = "information about all transactions of the selected account",
            description = "allows you to get information about all transactions of the selected account, requires the role of manager or user"
    )
    ResponseEntity<List<TransactionDto>> getAllTransactionsOfAccount(@PathVariable(name = "accountNumber") String accountNumber,
                                                                     Principal principal, HttpServletRequest request);

    @Operation(
            summary = "information about all transactions",
            description = "allows you to get information about all transactions, requires the role of manager"
    )
    ResponseEntity<List<TransactionDto>> getAllTransactions(Principal principal, HttpServletRequest request);

    @Operation(
            summary = "information about transaction of the selected id",
            description = "allows you to get information about transaction of the selected id, requires the role of manager"
    )
    ResponseEntity<TransactionDto> findById(@PathVariable(name = "id") UUID id, Principal principal, HttpServletRequest request);

    @Operation(
            summary = "information about all transactions of the selected client by tax code",
            description = "allows you to get information about all transactions of the selected client by tax code, requires the role of manager or user"
    )
    ResponseEntity<List<TransactionDto>> findByTaxCode(@PathVariable(name = "taxcode") String taxCode, Principal principal, HttpServletRequest request);

    @Operation(
            summary = "create a new transaction",
            description = "allows you to create a new transaction, requires the role of manager or user"
    )
    ResponseEntity<UUID> createNewTransaction(@RequestBody TransactionDto transactionDto, Principal principal, HttpServletRequest request);

    @Operation(
            summary = "correct wrong transaction",
            description = "allows you to correct wrong transaction, requires the role of manager"
    )
    ResponseEntity<UUID> reversTransaction(@PathVariable(name = "id") String id, Principal principal, HttpServletRequest request );
}
