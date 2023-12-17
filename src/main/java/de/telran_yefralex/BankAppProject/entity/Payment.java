package de.telran_yefralex.BankAppProject.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "agreement_id", referencedColumnName = "id", updatable = false)
    private Agreement agreementId;

    @Column(name = "payment_sum")
    private BigDecimal paymentSum;

    @Column(name = "expected_payment_date")
    private LocalDateTime expectedPaymentDate;

    @Column(name = "real_payment_date")
    private LocalDateTime realPaymentDate;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;
}
