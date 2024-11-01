package com.example.api_scotia.repository;

import com.example.api_scotia.entities.CardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardTypeRepository extends JpaRepository<CardTypeEntity, String> {

    Optional<CardTypeEntity> findByName(String name);
}
