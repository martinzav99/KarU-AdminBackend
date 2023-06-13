package com.ungspp1.gadminbackend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ungspp1.gadminbackend.model.entity.CreditAnalisysDE;

public interface CreditAnalisysRepository extends JpaRepository <CreditAnalisysDE , Long>{
    CreditAnalisysDE findByDocument (String document);   
}
