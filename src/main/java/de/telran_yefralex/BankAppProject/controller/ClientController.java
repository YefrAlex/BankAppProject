package de.telran_yefralex.BankAppProject.controller;


import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    public final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService=clientService;
    }

    @GetMapping("/find/all/short")
    public ResponseEntity<List<ClientShortDto>> findAllShort() {
        return clientService.findAllShort();
    }

    @GetMapping("/find/all/full")
    public ResponseEntity<List<ClientFullInfoDto>> findAllFullInfo() {
        return ResponseEntity.ok(clientService.findAllFullInfo());
    }

    @GetMapping("/find/taxcode/{taxCode}")
    public ResponseEntity<ClientShortDto> findByTaxCode(@PathVariable(name = "taxCode") String taxCode) {
        return ResponseEntity.ok(clientService.findClientByTaxCode(taxCode));
    }

    @GetMapping("/find/email/{email}")
    public ResponseEntity<ClientShortDto> findByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(clientService.findClientByEmail(email));
    }

    @PutMapping("/update/{taxCode}")
    public ResponseEntity<String> updateClient(
            @PathVariable(name = "taxCode") String taxCode,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "country", required = false) Country country,
            @RequestParam(name = "isBlocked", required = false) Boolean isBlocked) {
        clientService.updateClient(taxCode, firstName, lastName, email, address, phone, country, isBlocked);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNewClient(@RequestBody ClientFullInfoDto clientFullInfoDto) {
        Client client=clientService.createNewClient(clientFullInfoDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
