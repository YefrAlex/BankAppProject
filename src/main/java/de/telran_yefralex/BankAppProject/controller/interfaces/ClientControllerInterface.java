package de.telran_yefralex.BankAppProject.controller.interfaces;

import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Tag(name = "client controller", description = "allows you to create, edit and receive information about client")
public interface ClientControllerInterface {

    @Operation(
            summary = "short information about all clients",
            description = "allows you to get information about all clients, requires the role of manager"
    )
    ResponseEntity<ResponseEntity<List<ClientShortDto>>> findAllShort();

    @Operation(
            summary = "short information about client by tax code",
            description = "allows you to get information client by tax code, requires the role of manager"
    )
    ResponseEntity<ClientShortDto> findByTaxCode(@PathVariable(name = "taxCode") String taxCode);

    @Operation(
            summary = "short information about client by email",
            description = "allows you to get information client by email, requires the role of manager"
    )
    ResponseEntity<ClientShortDto> findByEmail(@PathVariable(name = "email") String email);

    @Operation(
            summary = "full information about all clients",
            description = "allows you to get information about all clients, requires the role of admin"
    )
    ResponseEntity<List<ClientFullInfoDto>> findAllFullInfo();

    @Operation(
            summary = "update information about client",
            description = "allows you to update information about client, requires the role of manager"
    )
    ResponseEntity<String> updateClient(
            @PathVariable(name = "taxCode") String taxCode,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "country", required = false) Country country,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked);

    @Operation(
            summary = "create a new client",
            description = "allows you to create a new client, requires the role of manager"
    )
    ResponseEntity<UUID> createNewClient(@RequestBody ClientFullInfoDto clientFullInfoDto);
}
