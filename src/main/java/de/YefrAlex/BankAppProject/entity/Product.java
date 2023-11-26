package de.YefrAlex.BankAppProject.entity;

import de.YefrAlex.BankAppProject.entity.enums.CurrencyCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "product_typ", nullable = false, unique = true)
    private String productTyp;

    @Column(name = "currency_code", nullable = false)
    private CurrencyCode currencyCode;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "limit_sum", nullable = false)
    private BigDecimal limit;

    @Column(name = "limit_duration", nullable = false)
    private int limitDuration;

    @Column(name = "description")
    @Size(max = 255, message = "Max size for description is 255 characters")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id) && Objects.equals(productTyp, product.productTyp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productTyp);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productTyp='" + productTyp + '\'' +
                ", currencyCode=" + currencyCode +
                ", interestRate=" + interestRate +
                ", limit=" + limit +
                ", limitDuration=" + limitDuration +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updateAt=" + updateAt +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
