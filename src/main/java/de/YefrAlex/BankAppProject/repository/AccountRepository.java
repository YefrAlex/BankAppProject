package de.YefrAlex.BankAppProject.repository;

import de.YefrAlex.BankAppProject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository <Account, UUID> {
    @Query("select a from Account a where a.accountNumber = :number")
    Account getByNumber(@Param("number") String number);
}
