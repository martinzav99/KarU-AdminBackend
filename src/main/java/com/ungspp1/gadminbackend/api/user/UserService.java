package com.ungspp1.gadminbackend.api.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ungspp1.gadminbackend.api.user.mapper.UserMapper;
import com.ungspp1.gadminbackend.api.user.to.UserTO;
import com.ungspp1.gadminbackend.exceptions.EngineException;
import com.ungspp1.gadminbackend.model.entity.UserDE;
import com.ungspp1.gadminbackend.model.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserTO getUserByUsername(String username) throws EngineException{
        if(username!=null && !username.trim().equals("")){
            Optional<UserDE> userOpt = userRepository.findByUsername(username);
            if (userOpt.isPresent()){
                return userMapper.userDEToTO(userOpt.get());
            } else {
                throw new EngineException("No user found with username " + username, HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new EngineException("The username can't be null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public List<UserTO> getUsersByType(String type) throws EngineException{
        if(type!=null && !type.trim().equals("")){
            List<UserDE> userList = userRepository.findByType(type);
            if (!userList.isEmpty()){
                return userMapper.userDEListToTO(userList);
            } else {
                throw new EngineException("No user found with type " + type, HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new EngineException("The type can't be null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public List<UserTO> getUsersByBranch(String branch) throws EngineException{
        if(branch!=null && !branch.trim().equals("")){
            List<UserDE> userList = userRepository.findByBranch(branch);
            if (!userList.isEmpty()){
                return userMapper.userDEListToTO(userList);
            } else {
                throw new EngineException("No user found for branch " + branch, HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new EngineException("The branch can't be null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public List<UserTO> getUsersByTechLevel(String level) throws EngineException{
        if(level!=null && !level.trim().equals("")){
            List<UserDE> userList = userRepository.findByTechnicalLevel(level);
            if (!userList.isEmpty()){
                return userMapper.userDEListToTO(userList);
            } else {
                throw new EngineException("No user found with technical level " + level, HttpStatus.BAD_REQUEST);
            }
        } else {
            throw new EngineException("The technical level can't be null or empty", HttpStatus.BAD_REQUEST);
        }
    }

    public List<UserTO> getAllUsers(){
        List<UserDE> userList = userRepository.findAll();
        return userMapper.userDEListToTO(userList);
    }

}
