package com.example.api_scotia.repository;

import com.example.api_scotia.entities.CardTypeEntity;
import com.example.api_scotia.entities.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity,String>{
    Optional<CardTypeEntity> findById(String Id);
}
