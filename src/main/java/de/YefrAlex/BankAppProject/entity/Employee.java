package de.YefrAlex.BankAppProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.entity.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false)
    private UUID id;

    @Column(name = "first_name")
    @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (other lowercase) and contain at least two letters")
    private String firstName;

    @Column(name = "last_name")
    @Pattern(regexp = "[A-Z][a-z]{1,49}", message = "a string should start with a capital letter (other lowercase) and contain at least two letters")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotBlank
    private Role role;

    @NotEmpty(message = "Email cant be empty")
    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Phone cant be empty")
    @Pattern(regexp = "\\+\\d{8,15}", message = "Phone is not valid")
    @Column(name = "phone")
    private String phone;

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

    @OneToMany(mappedBy = "mainManagerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Account> mainManagerAccounts = new HashSet<>();

    @OneToMany(mappedBy = "assistantManagerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Set<Account> assistantManagerAccounts = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee=(Employee) o;
        return id.equals(employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                ", createdAt=" + createdAt +
                ", updateAt=" + updatedAt +
                ", isBlocked=" + isBlocked +
                '}';
    }
}


