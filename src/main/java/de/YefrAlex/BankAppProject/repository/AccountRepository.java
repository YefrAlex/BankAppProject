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
//    @Query("select a from Account a where a.accountNumber = :number")
//    Optional<Account> getByNumber(@Param("number") String number);
//    @Query("select a from Account a where a.accountNumber = ?1")
//    List <Account> getByNumber( String number);

    @Query("select a from Account a where a.accountNumber = ?1")
    Optional <Account> getByNumber( String number);
    @Query("select a from Account a where a.accountNumber = ?1")
     Account getByNumber1( String number);
}
