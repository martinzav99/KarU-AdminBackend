package com.ungspp1.gadminbackend.api.modify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.modify.mapper.ModifyMapper;
import com.ungspp1.gadminbackend.api.modify.to.ModifyRequestTO;
import com.ungspp1.gadminbackend.api.modify.to.ModifyResponseTO;
import com.ungspp1.gadminbackend.entity.UserDE;
import com.ungspp1.gadminbackend.repository.UserRepository;

@Service
public class ModifyService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModifyMapper modifyMapper;

    public ModifyResponseTO updateUserData(ModifyRequestTO request){
        UserDE user = userRepository.findById(request.getId()).get();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.getContactData().setEmail(request.getEmail());
        userRepository.save(user);
        return modifyMapper.userDEToResponse(user);
    }
}