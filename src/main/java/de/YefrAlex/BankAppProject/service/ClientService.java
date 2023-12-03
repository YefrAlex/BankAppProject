package de.YefrAlex.BankAppProject.service;

import de.YefrAlex.BankAppProject.dto.ClientFullInfoDto;
import de.YefrAlex.BankAppProject.dto.ClientShortDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.mapper.ClientMapper;
import de.YefrAlex.BankAppProject.repository.ClientRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository=clientRepository;
        this.clientMapper=clientMapper;
    }

    public List<Client> findAll(){
        return  clientRepository.findAll();
    }

    public List<ClientShortDto> findAllShort() {
        List<Client> allClients = clientRepository.findAll();
        return allClients.stream()
                .map(clientMapper::toClientShortDto)
                .collect(Collectors.toList());
    }

    public ClientShortDto findClientById (UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ExpressionException("User not found with id " + id));
        return  clientMapper.toClientShortDto(client);
    }
    public ClientShortDto findClientByTaxCode (String taxCode) {
        Client client = clientRepository.findAll().stream().filter(cl->(taxCode).equals(cl.getTaxCode())).findFirst().orElseThrow(() -> new ExpressionException("User not found with taxcode " + taxCode));
        return  clientMapper.toClientShortDto(client);
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
}
