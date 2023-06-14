package com.ungspp1.gadminbackend.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ungspp1.gadminbackend.model.entity.CreditAnalisysDE;

public interface CreditAnalisysRepository extends JpaRepository <CreditAnalisysDE , Long>{
    Optional<CreditAnalisysDE> findByDocument (String document);   
}
