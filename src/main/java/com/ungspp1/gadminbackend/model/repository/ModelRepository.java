package com.ungspp1.gadminbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.ModelDE;

@Repository
public interface ModelRepository extends JpaRepository<ModelDE, Long> {
    
   ModelDE findByModelAndYear (String model , String year);

}

