package de.telran_yefralex.BankAppProject.repository;

import de.telran_yefralex.BankAppProject.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
