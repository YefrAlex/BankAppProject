package de.YefrAlex.BankAppProject.repository;

import de.YefrAlex.BankAppProject.entity.Agreement;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement,Long> {


}
