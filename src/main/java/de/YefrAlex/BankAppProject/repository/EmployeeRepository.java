package de.YefrAlex.BankAppProject.repository;

import de.YefrAlex.BankAppProject.dto.EmployeeDto;
import de.YefrAlex.BankAppProject.entity.Client;
import de.YefrAlex.BankAppProject.entity.Employee;
import de.YefrAlex.BankAppProject.entity.enums.Country;
import de.YefrAlex.BankAppProject.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    @Query("select e from Employee e where e.email = ?1")
    Employee findEmployeeByEmail (String email);

    @Query("select e from Employee e where e.firstName = ?1 AND e.lastName = ?2")
    Employee findEmployeeByName(String firstName, String lastName);

    @Query("select e from Employee e where e.role = ?1")
    List<Employee> getAllByRole(Role role);

    @Transactional
    @Modifying
    @Query("UPDATE Employee e " +
            "SET e.firstName = CASE WHEN :firstName IS NOT NULL THEN :firstName ELSE e.firstName END, " +
            "e.lastName = CASE WHEN :lastName IS NOT NULL THEN :lastName ELSE e.lastName END, " +
            "e.role = CASE WHEN :role IS NOT NULL THEN :role ELSE e.role END, " +
            "e.email = CASE WHEN :email IS NOT NULL THEN :email ELSE e.email END, " +
            "e.phone = CASE WHEN :phone IS NOT NULL THEN :phone ELSE e.phone END, " +
            "e.country = CASE WHEN :country IS NOT NULL THEN :country ELSE e.country END " +
            "WHERE e.id = :id")
    void updateEmployee (@Param("id") UUID id,
                         @Param("firstName") String firstName,
                         @Param("lastName") String lastName,
                         @Param("role") Role role,
                         @Param("email") String email,
                         @Param("phone") String phone,
                         @Param("country") Country country);



}
