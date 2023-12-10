package de.YefrAlex.BankAppProject.dto;

import lombok.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class ManagerForClientDto {
    String firstName;
    String lastName;
    String email;
    String phone;
}
