package com.example.api_scotia.repository;

import com.example.api_scotia.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByName(String name);
    List<RoleEntity> findByNameIn(List<String> roleNames);
}
