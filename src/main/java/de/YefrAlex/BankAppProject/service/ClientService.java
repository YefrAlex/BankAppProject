package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.advice.ResponseException;
import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import org.springframework.http.ResponseEntity;


import java.util.List;


public interface ClientService {

     ClientShortDto findClientByTaxCode (String taxCode) throws ResponseException;
     ClientShortDto findClientByEmail (String email);
     List<ClientFullInfoDto> findAllFullInfo();
     ResponseEntity<List<ClientShortDto>> findAllShort();
     void updateClient(String taxCode, String firstName, String lastName, String email, String address, String phone, Country country, Boolean isBlocked);
     Client createNewClient(ClientFullInfoDto clientFullInfoDto);
}
