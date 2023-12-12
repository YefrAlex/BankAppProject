package de.YefrAlex.BankAppProject.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.YefrAlex.BankAppProject.entity.enums.AccountType;
import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "client_id", referencedColumnName = "id", updatable = false, nullable = false)
    private Client clientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "main_manager_id", referencedColumnName = "id", nullable = false)
    private Employee mainManagerId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "assistant_manager_id", referencedColumnName = "id")
    private Employee assistantManagerId;

    @NotEmpty(message = "Account Number cant be empty")
    @Column(name = "account_number")
    @Size(min = 12, max = 20, message = "Account number must be between 12 and 20 characters long")
    private String accountNumber;

    @NotNull(message = "type cant be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private AccountType type;

    @Column(name = "balance", precision = 2)
    private BigDecimal balance;

    @NotNull(message = "Currency code cant be empty")
    @Enumerated(EnumType.STRING)
    @Column(name = "currency_code")
    private CurrencyCode currencyCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;

    @OneToMany(
            mappedBy = "debitAccountId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Transaction> debitTransactions = new HashSet<>();

    @OneToMany(
            mappedBy = "creditAccountId",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Transaction> creditTransactions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account=(Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        if (assistantManagerId != null) {
            return "Account{" +
                    "id=" + id +
                    ", clientId=" + clientId +
                    ", mainManagerId=" + mainManagerId +
                    ", assistantManagerId=" + assistantManagerId +
                    ", accountNumber='" + accountNumber + '\'' +
                    ", type=" + type +
                    ", balance=" + balance +
                    ", currencyCode=" + currencyCode +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", isBlocked=" + isBlocked +
                    '}';
        } else
            return "Account{" +
                    "id=" + id +
                    ", clientId=" + clientId +
                    ", managerId=" + mainManagerId +
                    ", accountNumber='" + accountNumber + '\'' +
                    ", type=" + type +
                    ", balance=" + balance +
                    ", currencyCode=" + currencyCode +
                    ", createdAt=" + createdAt +
                    ", updatedAt=" + updatedAt +
                    ", isBlocked=" + isBlocked +
                    '}';
    }
}
