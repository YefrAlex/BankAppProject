package de.telran_yefralex.BankAppProject.controller;


import de.telran_yefralex.BankAppProject.controller.interfaces.ClientControllerInterface;
import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.service.impl.ClientServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/client")
public class ClientController implements ClientControllerInterface {

    public final ClientServiceImpl clientService;


    public ClientController(ClientServiceImpl clientService) {
        this.clientService=clientService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/find/all/short")
    public ResponseEntity<ResponseEntity<List<ClientShortDto>>> findAllShort(Principal principal, HttpServletRequest request) {
        log.info("findAllShort clients was called by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(clientService.findAllShort());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/find/taxcode/{taxCode}")
    public ResponseEntity<ClientShortDto> findByTaxCode(@PathVariable(name = "taxCode") String taxCode, Principal principal, HttpServletRequest request) {
        log.info("client with taxCode = " + taxCode + " watched by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(clientService.findClientByTaxCode(taxCode));
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @GetMapping("/find/email/{email}")
    public ResponseEntity<ClientShortDto> findByEmail(@PathVariable(name = "email") String email, Principal principal, HttpServletRequest request) {
        log.info("client with email = " + email + " watched by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(clientService.findClientByEmail(email));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/find/all/full")
    public ResponseEntity<List<ClientFullInfoDto>> findAllFullInfo(Principal principal, HttpServletRequest request) {
        log.info("findAllFullInfo clients was called by admin " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.ok(clientService.findAllFullInfo());
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/update/{taxCode}")
    public ResponseEntity<String> updateClient(
            @PathVariable(name = "taxCode") String taxCode,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "country", required = false) Country country,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked,
            Principal principal, HttpServletRequest request){
        log.info("client with taxCode = " + taxCode + " updated by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        clientService.updateClient(taxCode, firstName, lastName, email, address, phone, country, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<UUID> createNewClient(@RequestBody ClientFullInfoDto clientFullInfoDto, Principal principal, HttpServletRequest request) {
        Client client = clientService.createNewClient(clientFullInfoDto);
        log.info("client with id = " + client.getId() + " created by manager " + principal.getName() + " from ip " + request.getRemoteAddr());
        return ResponseEntity.created(URI.create("/" + client.getId())).body(client.getId());
    }


}
