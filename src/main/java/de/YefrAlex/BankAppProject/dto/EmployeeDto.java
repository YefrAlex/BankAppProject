package de.YefrAlex.BankAppProject.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.entity.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class EmployeeDto {

    String firstName;
    String lastName;
    Role role;
    String email;
    String phone;
    Country country;
    boolean isBlocked;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    LocalDateTime updatedAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<Account> mainManagerAccounts;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<Account> assistantManagerAccounts;
}
