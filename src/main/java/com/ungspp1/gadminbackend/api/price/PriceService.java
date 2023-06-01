package com.ungspp1.gadminbackend.api.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;
import com.ungspp1.gadminbackend.model.repository.PriceRepository;


@Service
public class PriceService {
    @Autowired
    PriceRepository repository;

     public PriceHistoryDE save(PriceHistoryDE newPrice) {
        return repository.save(newPrice);
    }
    
}
