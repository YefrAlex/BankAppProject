package de.telran_yefralex.BankAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import de.telran_yefralex.BankAppProject.entity.enums.Role;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
public class EmployeeDto {
    String firstName;
    String lastName;
    Role role;
    String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    String password;
    String phone;
    Country country;
    boolean isBlocked;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime updatedAt;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    List<Account> mainManagerAccounts;
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    List<Account> assistantManagerAccounts;
}
