package com.ungspp1.gadminbackend.api.modify;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.modify.mapper.ModifyMapper;
import com.ungspp1.gadminbackend.api.modify.to.ChangePassRequestTO;
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
            if (request.getEmail() != null) {
                user.getContactData().setEmail(request.getEmail());
            }
            userRepository.save(user);
            return modifyMapper.userDEToResponse(user);
        } 
        else {
            throw new EngineException("The user wasn't found", HttpStatus.BAD_REQUEST);
        }
    }
    
    public ModifyResponseTO updateUserPassword(ChangePassRequestTO request) throws EngineException{
        Optional<UserDE> userOptional = userRepository.findByUsername(request.getUsername());
        
        if (!userOptional.isPresent())
            throw new EngineException("The user wasn't found", HttpStatus.BAD_REQUEST);

        UserDE user = userOptional.get();
        
        if (!validParameters(request))
            throw new EngineException("Error uploading new password, please insert the values", HttpStatus.BAD_REQUEST);

        if (sameOldPass(request, user))
            throw new EngineException("Error uploading new password, try not to use the old password", HttpStatus.BAD_REQUEST);
     
        if (!validPass(request))
            throw new EngineException("Error uploading new password, not valid password", HttpStatus.BAD_REQUEST);

        user.setPassword(request.getNewPassword());          
        userRepository.save(user);
        return modifyMapper.userDEToResponse(user);
    }

    private boolean sameOldPass(ChangePassRequestTO request ,UserDE user){
        return request.getOldPassword().equals(user.getPassword());
    }

    private boolean validParameters(ChangePassRequestTO request ){
        return  request.getOldPassword() != null && request.getNewPassword() != null;
    }

    private boolean validPass(ChangePassRequestTO request){
    
        String password = request.getNewPassword();

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