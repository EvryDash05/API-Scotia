package com.example.api_scotia.repository;

import com.example.api_scotia.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, String> {
    @Query("SELECT l FROM LoanEntity l WHERE l.customer.customerId = :customerId")
    List<LoanEntity> findLoansByCustomerId(@Param("customerId") String customerId);
}
