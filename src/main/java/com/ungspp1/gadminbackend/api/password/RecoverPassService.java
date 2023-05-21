package com.ungspp1.gadminbackend.api.password;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.mail.SendMailFacade;
import com.ungspp1.gadminbackend.api.password.to.NewPassRequest;
import com.ungspp1.gadminbackend.api.password.to.RecoverPassRequestTO;
import com.ungspp1.gadminbackend.api.password.to.TokenRequestTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import com.ungspp1.gadminbackend.model.entity.UserResetPassDE;
import com.ungspp1.gadminbackend.model.repository.UserRepository;
import com.ungspp1.gadminbackend.model.repository.UserResetPassRepository;

@Service
public class RecoverPassService {
    
    @Autowired
    private UserResetPassRepository userResetPassRepository;

    @Autowired 
    private UserRepository userRepository;

    @Autowired
    private SendMailFacade mailFacade;

    public HttpStatus verifyEmail(RecoverPassRequestTO request) throws EngineException{

        Optional<UserDE> user = userRepository.findByEmail(request.getEmail());

        if(user.isPresent()){
           
            String code = RandomStringUtils.randomAlphanumeric(150).toUpperCase();
            UserResetPassDE token = UserResetPassDE.builder()
            .token(code)
            .codeGenerationDate(LocalDateTime.now())
            .codeExpirationDate(LocalDateTime.now().plusHours(3))
            .userData(user.get()).build();

            userResetPassRepository.save(token);
            mailFacade.sendTokenMail(request.getEmail(), code);
            return HttpStatus.OK;
        }
        else {
            throw new EngineException("The user wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    public Boolean verifyToken(TokenRequestTO request) throws EngineException{
        Optional<UserResetPassDE> tokenFound = userResetPassRepository.findByToken(request.getToken());
        boolean exist = tokenFound.isPresent();
        boolean isAvailable = tokenFound.get().getCodeExpirationDate().isAfter(LocalDateTime.now());
        return exist && isAvailable;
    }

    public HttpStatus uploadNewPass(NewPassRequest request) throws EngineException{
        Optional<UserDE> user =userRepository.findByEmail(request.getEmail());

        if (user.isPresent()){
            UserDE userFounded = user.get();
            if (request.getPassword() != null && !request.getPassword().equals(userFounded.getPassword())){
                userFounded.setPassword(request.getPassword());
                userRepository.save(userFounded);
                userResetPassRepository.DeleteByUsername(userFounded.getUsername());
                return HttpStatus.OK;
            }
            else{
                throw new EngineException("Error uploading new password, try not to use the old password", HttpStatus.BAD_REQUEST);
            }        
        }
        else{
            throw new EngineException("The user wasn't found", HttpStatus.BAD_REQUEST);
        }
    }
}
