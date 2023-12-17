package de.telran_yefralex.BankAppProject.repository;

import de.telran_yefralex.BankAppProject.entity.Agreement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement,Long> {


}
