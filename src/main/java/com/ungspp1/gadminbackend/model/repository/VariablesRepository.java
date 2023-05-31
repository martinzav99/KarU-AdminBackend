package com.ungspp1.gadminbackend.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.VariableDE;

@Repository
public interface VariablesRepository extends JpaRepository<VariableDE, Long> {
    
   Optional<VariableDE> findByName (String name);
}