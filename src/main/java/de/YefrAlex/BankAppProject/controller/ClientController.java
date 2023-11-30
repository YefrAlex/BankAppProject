package de.YefrAlex.BankAppProject.controller;

import de.YefrAlex.BankAppProject.service.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController  {

    public final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService=clientService;
    }
}
