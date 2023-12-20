package de.telran_yefralex.BankAppProject.service;




import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;


public interface ClientService {




     void updateClient(String taxCode, String firstName, String lastName, String email, String address, String phone, Country country, Boolean isBlocked);
     Client createNewClient(ClientFullInfoDto clientFullInfoDto);


     ResponseEntity<List<ClientShortDto>> findAllShort();
     ClientShortDto findClientByTaxCode (String taxCode);
     ClientShortDto findClientByEmail (String email);
     List<ClientFullInfoDto> findAllFullInfo();



}
