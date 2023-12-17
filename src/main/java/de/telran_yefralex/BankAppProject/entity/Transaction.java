package de.telran_yefralex.BankAppProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.telran_yefralex.BankAppProject.entity.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Account debitAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "credit_account_id", referencedColumnName = "id", nullable = false, updatable = false)
    private Account creditAccountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TransactionType type;

    @Column(name = "amount", precision = 2)
    private BigDecimal amount;

    @Column(name = "description")
    @Size(max = 255, message = "Max size for description is 255 characters")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(debitAccountId, that.debitAccountId) && Objects.equals(creditAccountId, that.creditAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, debitAccountId, creditAccountId);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", debitAccountId=" + debitAccountId +
                ", creditAccountId=" + creditAccountId +
                ", type=" + type +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

