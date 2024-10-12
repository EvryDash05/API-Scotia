package com.example.api_scotia.repository;

import com.example.api_scotia.entities.LiquidityTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquidityTransactionRepository extends JpaRepository<LiquidityTransactionEntity, String> {
}
