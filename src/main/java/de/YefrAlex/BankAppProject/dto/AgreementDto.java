package de.YefrAlex.BankAppProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.YefrAlex.BankAppProject.entity.Account;
import de.YefrAlex.BankAppProject.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
public class AgreementDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String accountNumber;
    private Long productId;
    private BigDecimal interestRate;
    private BigDecimal amount;
    private Integer duration;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean isBlocked;
}
