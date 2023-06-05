package com.ungspp1.gadminbackend.api.priceHistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.model.entity.PriceHistoryDE;
import com.ungspp1.gadminbackend.model.repository.PriceHistoryRepository;


@Service
public class PriceHistoryService {

    @Autowired
    private PriceHistoryRepository repository;

    public PriceHistoryDE save(PriceHistoryDE newPrice) {
        return repository.save(newPrice);
    }

    public List <PriceHistoryDE> getAll() {
      return repository.findAll();
    }
    
}
