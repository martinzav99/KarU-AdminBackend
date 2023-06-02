package com.ungspp1.gadminbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.ModelDE;

import feign.Param;

@Repository
public interface ModelRepository extends JpaRepository<ModelDE, Long> {
    
   ModelDE findByModelAndYear (String model , String year);

   @Query(value = "select m "+ 
      "from ModelDE m "+
      "where m.brand = :brand "+
      "and m.model = :model "+
      "and m.year = :year "+
      "and m.engine = :engine "+
      "and m.fuelType = :fuelType")
   ModelDE findModel (@Param("brand") String brand, @Param("model") String model, @Param("year") String year, @Param("engine") String engine, @Param("fuelType") String fuelType);

}

