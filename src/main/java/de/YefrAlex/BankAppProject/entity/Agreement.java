package de.YefrAlex.BankAppProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "agreements")
public class Agreement {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", updatable = false)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "account_id", referencedColumnName = "id", updatable = false)
    private Account accountId;

    @OneToOne (fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "product_id", referencedColumnName = "id", updatable = false)
    private Product productId;

    @Column(name = "interest_rate")
    private BigDecimal interestRate;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "duration", nullable = false)
    private int duration;

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
}

/*
Table agreements {
   




 */