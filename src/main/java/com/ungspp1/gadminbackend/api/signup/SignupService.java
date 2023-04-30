package com.ungspp1.gadminbackend.api.signup;

import com.ungspp1.gadminbackend.api.signup.mapper.SignupUserMapper;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserRequestTO;
import com.ungspp1.gadminbackend.api.signup.to.SignupUserResponseTO;
import com.ungspp1.gadminbackend.entity.UserDE;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.repository.UserRepository;
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
            throw new EngineException("An unexpected error has ocurred while saving the user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
