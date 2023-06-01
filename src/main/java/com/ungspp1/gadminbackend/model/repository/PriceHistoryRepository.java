package com.ungspp1.gadminbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistoryDE, Long> {
    
}
