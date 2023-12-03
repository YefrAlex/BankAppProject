package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController  {

    public final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService=clientService;
    }

    @GetMapping("/find/all/short")
    public ResponseEntity<List<ClientShortDto>> findAllShort() {
        return ResponseEntity.ok(clientService.findAllShort());
    }
    @GetMapping("/find/taxcode/{taxCode}")
    public ResponseEntity<ClientShortDto> findByTaxCode(@PathVariable(name = "taxCode") String taxCode) {
        return ResponseEntity.ok(clientService.findClientByTaxCode(taxCode));
    }
    @GetMapping("/find/email/{email}")
    public ResponseEntity<ClientShortDto> findByEmail(@PathVariable(name = "email") String email) {
        return ResponseEntity.ok(clientService.findClientByEmail(email));
    }

    @GetMapping("/find/all/full")
    public ResponseEntity<List<ClientFullInfoDto>> findAllFullInfo() {
        return ResponseEntity.ok(clientService.findAllFullInfo());
    }


}
