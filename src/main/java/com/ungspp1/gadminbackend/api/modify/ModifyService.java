package com.ungspp1.gadminbackend.api.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.repository.UserRepository;

@Service
public class ModifyService{
    
    @Autowired
    private UserRepository userRepository;

    public void updateUser(Long id){
        
    }
}