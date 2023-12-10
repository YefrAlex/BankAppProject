package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.enums.Country;


import java.util.List;
import java.util.UUID;


public interface ClientService {

     ClientShortDto findClientById (UUID id);
     ClientShortDto findClientByTaxCode (String taxCode);
     ClientShortDto findClientByEmail (String email);
     List<ClientFullInfoDto> findAllFullInfo();
     void updateClient(String taxCode, String firstName, String lastName, String email, String address, String phone, Country country);
     Client createNewClient(ClientFullInfoDto clientFullInfoDto);
}
