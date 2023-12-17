package de.YefrAlex.BankAppProject.service.impl;

import de.YefrAlex.BankAppProject.advice.ResponseException;
import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.mapper.ClientMapper;
import de.YefrAlex.BankAppProject.repository.ClientRepository;
import de.YefrAlex.BankAppProject.service.ClientService;
import org.springframework.expression.ExpressionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository=clientRepository;
        this.clientMapper=clientMapper;
    }



    public ResponseEntity<List<ClientShortDto>> findAllShort() {
        List<Client> allClients = clientRepository.findAll();
        if (allClients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ClientShortDto> DtoList =  allClients.stream()
                .map(clientMapper::toClientShortDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DtoList);
    }

    public ClientShortDto findClientByTaxCode (String taxCode) throws ResponseException {
        ClientShortDto clientDto;
        if (taxCode != null) {
        Client client = clientRepository.findClientByTaxCode(taxCode);
        if (client != null ){
            clientDto = clientMapper.toClientShortDto(client);
        }else throw new ResponseException("User not found with taxcode " + taxCode);
        } else throw new ResponseException("Enter taxCode please!");

        return clientDto;
    }
    public ClientShortDto findClientByEmail (String email) {
        Client client = clientRepository.findAll().stream().filter(cl->(email).equals(cl.getEmail())).findFirst().orElseThrow(() -> new ExpressionException("User not found with email " + email));
        return  clientMapper.toClientShortDto(client);
    }

    public List<ClientFullInfoDto> findAllFullInfo() {
        List<Client> allClients = clientRepository.findAll();
        return allClients.stream()
                .map(clientMapper::toClientFullInfoDto)
                .collect(Collectors.toList());
    }

    public void updateClient(String taxCode, String mainManagerId, String assistantManagerId, String email, String address, String phone, Country country,Boolean isBlocked) {
        if (isBlocked != null){
            Client client = clientRepository.findClientByTaxCode(taxCode);
            client.setBlocked(isBlocked);
            clientRepository.save(client);
        }
        clientRepository.updateClient(taxCode, mainManagerId, assistantManagerId, email, address, phone, country);
    }

    public Client createNewClient(ClientFullInfoDto clientFullInfoDto) {
        Client client = clientMapper.toClient(clientFullInfoDto);
        client.setId(null);
        client.setCreditRating(0);
        return clientRepository.save(client);
    }
}
