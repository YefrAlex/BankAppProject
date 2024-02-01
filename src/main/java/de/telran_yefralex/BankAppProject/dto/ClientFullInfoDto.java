package de.telran_yefralex.BankAppProject.dto;


import com.fasterxml.jackson.annotation.*;
import de.telran_yefralex.BankAppProject.entity.enums.Country;

import jakarta.validation.constraints.Pattern;
import lombok.*;


import java.time.LocalDateTime;

import java.util.List;


@Getter
@Setter
public class ClientFullInfoDto {

    String firstName;
    String lastName;
    String taxCode;
    String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    String address;
    String phone;
    Country country;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime updatedAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<AccountForClientDto> accounts;
}

