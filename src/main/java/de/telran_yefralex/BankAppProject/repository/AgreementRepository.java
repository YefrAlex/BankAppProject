package de.telran_yefralex.BankAppProject.repository;

import de.telran_yefralex.BankAppProject.entity.Agreement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement,Long> {

    @Query("SELECT a FROM Agreement a " +
            "JOIN a.accountId acc " +
            "JOIN acc.clientId c " +
            "WHERE c.email = ?1")
    List<Agreement> findMyAgreement(String userEmail);
}
