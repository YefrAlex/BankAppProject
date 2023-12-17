package de.YefrAlex.BankAppProject.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class ClientShortDto {
    String firstName;
    String lastName;
    String taxCode;
    String email;
    String phone;
    int numberOfAccounts;
}
