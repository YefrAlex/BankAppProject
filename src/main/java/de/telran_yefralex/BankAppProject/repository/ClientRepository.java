package de.telran_yefralex.BankAppProject.repository;

import de.telran_yefralex.BankAppProject.entity.Client;
import de.telran_yefralex.BankAppProject.entity.enums.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query("select c from Client c where c.taxCode = ?1")
    Client findClientByTaxCode (String taxCode);

    @Query("select c from Client c where c.email = ?1")
    Client findClientByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Client c " +
            "SET c.firstName = CASE WHEN :firstName IS NOT NULL THEN :firstName ELSE c.firstName END, " +
            "c.lastName = CASE WHEN :lastName IS NOT NULL THEN :lastName ELSE c.lastName END, " +
            "c.email = CASE WHEN :email IS NOT NULL THEN :email ELSE c.email END, " +
            "c.address = CASE WHEN :address IS NOT NULL THEN :address ELSE c.address END, " +
            "c.phone = CASE WHEN :phone IS NOT NULL THEN :phone ELSE c.phone END, " +
            "c.country = CASE WHEN :country IS NOT NULL THEN :country ELSE c.country END " +
            "WHERE c.taxCode = :taxCode")
    void updateClient (@Param("taxCode") String taxCode,
                       @Param("firstName") String firstName,
                       @Param("lastName") String lastName,
                       @Param("email") String email,
                       @Param("address") String address,
                       @Param("phone") String phone,
                       @Param("country") Country country);

}
