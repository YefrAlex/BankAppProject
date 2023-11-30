package de.YefrAlex.BankAppProject.repository;

import de.YefrAlex.BankAppProject.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
