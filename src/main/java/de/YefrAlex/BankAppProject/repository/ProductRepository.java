package de.YefrAlex.BankAppProject.repository;

import de.YefrAlex.BankAppProject.entity.Product;
import de.YefrAlex.BankAppProject.entity.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.isBlocked = false")
    List<Product> findAllActiveProducts();

    @Query("select p from Product p where p.productType = :productType")
    List<Product> findActiveProductsWithType( @Param("productType") ProductType productType);

    Product getProductDtoById(Long id);
}
