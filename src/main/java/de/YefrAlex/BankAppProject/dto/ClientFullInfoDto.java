package de.YefrAlex.BankAppProject.dto;

import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import de.YefrAlex.BankAppProject.mapper.AccountMapper;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
public class ClientFullInfoDto {

    String firstName;
    String lastName;
    String taxCode;
    String email;
    String address;
    String phone;
    LocalDateTime createdAt;
    List<AccountForClientDto> accounts;

}

