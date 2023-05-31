package com.ungspp1.gadminbackend.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.VehicleDE;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleDE, Long> {
    
    @Query(value = "select u from VehicleDE u where u.plate = :plate")
    Optional<VehicleDE> findByPlate(@Param("plate") String plate);

    @Query(value = "select u from VehicleDE u where u.status = :status")
    List<VehicleDE> findByStatus(@Param("status") String status);
}
