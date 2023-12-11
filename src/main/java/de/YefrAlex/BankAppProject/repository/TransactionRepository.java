package de.YefrAlex.BankAppProject.repository;

import de.YefrAlex.BankAppProject.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t JOIN t.debitAccountId da JOIN t.creditAccountId ca WHERE da.accountNumber=:account OR ca.accountNumber=:account")
    List<Transaction> getAllTransactionsWithAccountNumber(@Param("account") String account);

    @Query("SELECT t FROM Transaction t JOIN t.debitAccountId da JOIN t.creditAccountId ca " +
            "JOIN da.clientId cda JOIN ca.clientId cca WHERE cda.taxCode=:taxCode OR cca.taxCode=:taxCode")
    List<Transaction> getAllTransactionsWithTaxCode(String taxCode);
}
