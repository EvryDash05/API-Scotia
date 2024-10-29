package com.example.api_scotia.repository;

import com.example.api_scotia.entities.ClientPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPaymentRepository extends JpaRepository<ClientPaymentEntity, String> {
}
