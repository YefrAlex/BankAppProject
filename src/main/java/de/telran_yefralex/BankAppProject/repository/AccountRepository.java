package de.telran_yefralex.BankAppProject.repository;

import de.telran_yefralex.BankAppProject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository <Account, UUID> {

    @Query("select a from Account a where a.accountNumber = ?1")
    Optional <Account> getByNumber( String number);

    @Query("select a from Account a JOIN a.clientId c where c.email = ?1")
    List<Account> findAllClientAccounts(String clientEMail);
    @Query("select a from Account a JOIN a.mainManagerId mm JOIN a.assistantManagerId am " +
            "where mm.email = ?1 OR am.email = ?1")
    List<Account> findManagerAll(String managerEmail);
}
