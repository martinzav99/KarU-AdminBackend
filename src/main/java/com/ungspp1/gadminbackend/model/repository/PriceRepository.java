package com.ungspp1.gadminbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;

public interface PriceRepository extends JpaRepository<PriceHistoryDE, Long> {
}
