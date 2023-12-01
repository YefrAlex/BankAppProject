package de.YefrAlex.BankAppProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @UuidGenerator(style=UuidGenerator.Style.TIME)
    private UUID id;


    @Column(name = "first_name")
    @Pattern(regexp = "[A-Z][a-z]{1,}", message = "a string should start with a capital letter (other lowercase) and contain at least two letters")
    private String firstName;


    @Column(name = "last_name")
    @Pattern(regexp = "[A-Z][a-z]{1,}", message = "a string should start with a capital letter (other lowercase) and contain at least two letters")
    private String lastName;

    @NotBlank(message = "Tax Code cant be empty")
    @Column(name = "tax_code")
    @Size(min = 12, max = 20, message = "Tax code must be between 12 and 20 characters long")
    private String taxCode;

    @Column(name = "credit_rating", nullable = false, columnDefinition = "int default 0")
    private Integer creditRating;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email is not valid")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Phone cant be empty")
    @Pattern(regexp = "\\+\\d{8,15}", message = "Phone is not valid")
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Address cant be empty")
    @Size(min = 10, max = 80, message = "Last name must be between 10 and 80 characters long")
    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "country")
    @NotBlank
    private Country country;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;

    @OneToMany(mappedBy = "clientId", fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Account> accounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client=(Client) o;
        return id.equals(client.id) && taxCode.equals(client.taxCode) && email.equals(client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taxCode, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", taxCode='" + taxCode + '\'' +
                ", creditRating=" + creditRating +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", country=" + country +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isBlocked=" + isBlocked +
                ", accounts=" + accounts +
                '}';
    }
}
