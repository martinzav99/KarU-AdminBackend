package com.ungspp1.gadminbackend.api.login;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.login.mapper.LoginMapper;
import com.ungspp1.gadminbackend.api.login.to.LoginRequestTO;
import com.ungspp1.gadminbackend.api.login.to.LoginResponseTO;
import com.ungspp1.gadminbackend.entity.UserDE;
import com.ungspp1.gadminbackend.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginMapper loginMapper;

    public LoginResponseTO getUserByUsername(String username){
        Optional<UserDE> userDE = userRepository.findByUsername(username);
        return validatePresentUser(userDE);
    }

    public LoginResponseTO getUserByUsernameAndPassword(LoginRequestTO request){
        Optional<UserDE> userDE = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        return validatePresentUser(userDE);
    }
    
    private LoginResponseTO validatePresentUser(Optional<UserDE> userDE) {
        LoginResponseTO response;
        if(userDE.isPresent()){
            response = loginMapper.userDEToResponseTO(userDE.get());
        } else {
            response = loginMapper.userNotFound();
        }
        return response;
    }
    
}
