package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.dto.ProductDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.service.impl.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController  {

    public final ClientServiceImpl clientService;


    public ClientController(ClientServiceImpl clientService) {
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

    @PutMapping("/update/{taxCode}")
    public ResponseEntity<String> updateClient(
            @PathVariable(name = "taxCode") String taxCode,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "country", required = false) Country country){
        clientService.updateClient(taxCode, firstName, lastName, email, address, phone, country);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<UUID> createNewClient(@RequestBody ClientFullInfoDto clientFullInfoDto) {
        Client client = clientService.createNewClient(clientFullInfoDto);
        return ResponseEntity.created(URI.create("/" + client.getId())).body(client.getId());
    }


}
