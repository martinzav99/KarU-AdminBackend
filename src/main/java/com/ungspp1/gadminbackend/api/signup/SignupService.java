package com.ungspp1.gadminbackend.api.signup;

import com.ungspp1.gadminbackend.api.signup.mapper.SignupUserMapper;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserResponseTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import com.ungspp1.gadminbackend.model.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SignupUserMapper signupUserMapper;

    public SignupUserResponseTO saveUser(SignupUserRequestTO request) throws EngineException {
        UserDE user = signupUserMapper.SignupUserRequestToDE(request);
        try {
            UserDE savedUser = userRepository.save(user);
            return signupUserMapper.UserDEToResponse(savedUser);
        } catch (Exception e) {
            throw new EngineException("Ocurrio un error al guardar el usuario", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public void validateRepeatedContact(SignupUserRequestTO request) throws EngineException {
        Optional<UserDE> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()){
            throw new EngineException("El email ya esta en uso", HttpStatus.BAD_REQUEST);
        } else {
            user = userRepository.findByPhone(request.getPhoneCode(), request.getPhoneNumber());
            if(user.isPresent()){
                throw new EngineException("El telefono ya esta en uso", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
