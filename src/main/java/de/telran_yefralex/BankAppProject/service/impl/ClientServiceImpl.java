package de.telran_yefralex.BankAppProject.service.impl;

import de.telran_yefralex.BankAppProject.dto.ClientFullInfoDto;
import de.telran_yefralex.BankAppProject.dto.ClientShortDto;
import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.exceptions.ErrorMessage;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.ClientNotFoundException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmailIsUsedException;
import de.telran_yefralex.BankAppProject.exceptions.exceptionslist.EmptyClientsListException;
import de.telran_yefralex.BankAppProject.mapper.ClientMapper;
import de.telran_yefralex.BankAppProject.repository.ClientRepository;
import de.telran_yefralex.BankAppProject.service.ClientService;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, PasswordEncoder passwordEncoder) {
        this.clientRepository=clientRepository;
        this.clientMapper=clientMapper;
        this.passwordEncoder=passwordEncoder;
    }
    public ResponseEntity<List<ClientShortDto>> findAllShort() {
        List<Client> allClients=clientRepository.findAll();
        if (allClients.isEmpty()) {
            throw new EmptyClientsListException(ErrorMessage.EMPTY_CLIENTS_LIST);
        }
        List<ClientShortDto> DtoList=allClients.stream()
                .map(clientMapper::toClientShortDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(DtoList);
    }
    public ClientShortDto findClientByTaxCode(String taxCode) {
        Client client=clientRepository.findClientByTaxCode(taxCode);
        if (client == null) {
            throw new ClientNotFoundException(ErrorMessage.CLIENT_NOT_FOUND, taxCode);
        }
        return clientMapper.toClientShortDto(client);
    }
    public ClientShortDto findClientByEmail(String email) {
        Client client=clientRepository.findClientByEmail(email);
        if (client == null) {
            throw new ClientNotFoundException(ErrorMessage.CLIENT_NOT_FOUND, email);
        }
        return clientMapper.toClientShortDto(client);
    }
    public List<ClientFullInfoDto> findAllFullInfo() {
        List<Client> allClients=clientRepository.findAll();
        if (allClients.isEmpty()) {
            throw new EmptyClientsListException(ErrorMessage.EMPTY_CLIENTS_LIST);
        }
        return allClients.stream()
                .map(clientMapper::toClientFullInfoDto)
                .collect(Collectors.toList());
    }
    public void updateClient(String taxCode, String firstName, String lastName, String email, String address, String phone, Country country, Boolean isBlocked) {
        Client client=clientRepository.findClientByTaxCode(taxCode);
        if (client == null) {
            throw new ClientNotFoundException(ErrorMessage.CLIENT_NOT_FOUND, taxCode);
        }
        if (isBlocked != null) {
            client.setBlocked(isBlocked);
            clientRepository.save(client);
        }
        clientRepository.updateClient(taxCode, firstName, lastName, email, address, phone, country);
    }
    public Client createNewClient(ClientFullInfoDto clientFullInfoDto) {
        if (clientRepository.findClientByEmail(clientFullInfoDto.getEmail())!= null){
            throw new EmailIsUsedException(ErrorMessage.IS_USED, clientFullInfoDto.getEmail());
        }
        Client client=clientMapper.toClient(clientFullInfoDto);
        client.setId(null);
        client.setPassword(passwordEncoder.encode(clientFullInfoDto.getPassword()));
        client.setCreditRating(0);
        return clientRepository.save(client);
    }

}
