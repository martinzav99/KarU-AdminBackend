package com.ungspp1.gadminbackend.api.password;

import java.time.LocalDateTime;
import java.util.List;
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
            mailFacade.sendTokenMail(user.get().getUsername(),request.getEmail(), code);
            return HttpStatus.OK;
        }
        else {
            throw new EngineException("The user wasn't found", HttpStatus.BAD_REQUEST);
        }
    }

    public HttpStatus verifyToken(TokenRequestTO request) throws EngineException{
        Optional<UserResetPassDE> tokenFound = userResetPassRepository.findByToken(request.getToken());
        
        if(!tokenFound.isPresent())
            throw new EngineException("Token wasn't found", HttpStatus.BAD_REQUEST);

        if (!tokenFound.get().getCodeExpirationDate().isAfter(LocalDateTime.now()))
            throw new EngineException("Token is expired", HttpStatus.BAD_REQUEST);
       
        return HttpStatus.OK;
    }

    public HttpStatus uploadNewPass(NewPassRequest request) throws EngineException{
        Optional<UserDE> user =userRepository.findByEmail(request.getEmail());

        if (!user.isPresent())
            throw new EngineException("The user wasn't found", HttpStatus.BAD_REQUEST);
        
        UserDE userFounded = user.get();

        if (request.getPassword() == null)
            throw new EngineException("Error uploading new password", HttpStatus.BAD_REQUEST); 

        if (sameOldPass(request, userFounded))
            throw new EngineException("Error uploading new password, try not to use the old password", HttpStatus.BAD_REQUEST);
        
        if(!validPass(userFounded))
            throw new EngineException("Error uploading new password, not valid password", HttpStatus.BAD_REQUEST);
   
        userFounded.setPassword(request.getPassword());
        userRepository.save(userFounded);
        return HttpStatus.OK;                      
    }

    private boolean sameOldPass(NewPassRequest request ,UserDE user){
        return request.getPassword().equals(user.getPassword());
    }

    private boolean validPass(UserDE user){
    
        String password = user.getPassword();

        if (8 <= password.length() && password.length()<=20){
            
            boolean hasMayus= false, hasMin= false, hasNumb= false, hasEspecial = false;

            for (int i = 0; i< password.length(); i++){
                if (ascciRange(password, i, 97, 122))
                    hasMin = true;
                    
                if (ascciRange(password, i, 65, 90))
                    hasMayus = true;
                    
                if (ascciRange(password, i, 48, 57))
                    hasNumb = true;
                    
                if (ascciRange(password,i, 33, 47) || ascciRange(password,i, 58, 64) || ascciRange(password,i, 91, 96))
                    hasEspecial = true;          
            }
            return hasMayus && hasMin && hasNumb && hasEspecial;
        }
        else {
            return false;
        }
    }

    private boolean ascciRange(String word, int pos, int a , int b ){
        return a <= word.charAt(pos) && word.charAt(pos) <= b;
    }
}
