package de.YefrAlex.BankAppProject.dto;

import lombok.*;

@Getter
@Setter
public class ClientShortDto {
    String firstName;
    String lastName;
    String taxCode;
    String email;
    String phone;
    int numberOfAccounts;



    public ClientShortDto() {
    }

}
