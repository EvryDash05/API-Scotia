package com.example.api_scotia.repository;

import com.example.api_scotia.entities.CustomerFinancialInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFinancialInfoRepository extends JpaRepository<CustomerFinancialInfoEntity, String> {

}
