package de.telran_yefralex.BankAppProject.repository;

import de.telran_yefralex.BankAppProject.entity.Product;
import de.telran_yefralex.BankAppProject.entity.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.isBlocked = false")
    List<Product> findAllActiveProducts();

    @Query("select p from Product p where p.productType = :productType")
    List<Product> findActiveProductsWithType( @Param("productType") ProductType productType);

    Product getProductDtoById(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p " +
            "SET p.isBlocked = CASE WHEN :isBlocked IS NOT NULL THEN :isBlocked ELSE p.isBlocked END, " +
            "p.interestRate = CASE WHEN :interestRate IS NOT NULL THEN :interestRate ELSE p.interestRate END, " +
            "p.limit = CASE WHEN :limit IS NOT NULL THEN :limit ELSE p.limit END, " +
            "p.limitDuration = CASE WHEN :limitDuration IS NOT NULL THEN :limitDuration ELSE p.limitDuration END " +
            "WHERE p.id = :id")
    void updateProduct(@Param("id") Long id,
                       @Param("interestRate") BigDecimal interestRate,
                       @Param("limit") BigDecimal limit,
                       @Param("limitDuration") Integer limitDuration,
                       @Param("isBlocked") Boolean isBlocked);
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.limit = :limit WHERE p.id = :id")
    void updateProductLimit(@Param("id") Long id,
                            @Param("limit") BigDecimal limit);

}
