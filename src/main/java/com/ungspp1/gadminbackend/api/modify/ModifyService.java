package com.ungspp1.gadminbackend.api.modify;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.modify.mapper.ModifyMapper;
import com.ungspp1.gadminbackend.api.modify.to.ModifyRequestTO;
import com.ungspp1.gadminbackend.api.modify.to.ModifyResponseTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import com.ungspp1.gadminbackend.model.repository.UserRepository;

@Service
public class ModifyService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModifyMapper modifyMapper;

    public ModifyResponseTO updateUserData(ModifyRequestTO request) throws EngineException{
        Optional<UserDE> userOptional = userRepository.findByUsername(request.getUsername());
        
        if(userOptional.isPresent()){
            UserDE user = userOptional.get();
            if (request.getPassword() != null){
                user.setPassword(request.getPassword());
            }
            if (request.getEmail() != null) {
                user.getContactData().setEmail(request.getEmail());
            }
                userRepository.save(user);
                return modifyMapper.userDEToResponse(user);
        } else {
            throw new EngineException("The user wasn't found", HttpStatus.BAD_REQUEST);
        }
    }
    
}