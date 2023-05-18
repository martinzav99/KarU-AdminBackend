package com.ungspp1.gadminbackend.api.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ungspp1.gadminbackend.api.user.to.UserTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;
    
    public UserTO getByUsername(String username) throws EngineException{
        return userService.getUserByUsername(username);
    } 

    public List<UserTO> getByType(String type) throws EngineException{
        return userService.getUsersByType(type);
    } 

    public List<UserTO> getByBranch(String branch) throws EngineException{
        return userService.getUsersByBranch(branch);
    } 

    public List<UserTO> getByTechLevel(String level) throws EngineException{
        return userService.getUsersByTechLevel(level);
    } 

    public List<UserTO> getAllUsers(){
        return userService.getAllUsers();
    } 
}
