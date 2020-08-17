package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Role;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.UserRepository;

public class UserService {
    //print method instantiation signiture

    private UserRepository userRepository;

    public UserService(UserRepository repo){

        LogService.log("Instantiating " + this.getClass().toString());
        userRepository = repo;

    }

    public User register(User newUser){
        if (!isUserValid(newUser)){
            LogService.log("Invalid user fields provided during registration");
            throw new InvalidRequestException("Invalid user fields provided during registration");
        }
        if (userRepository.findUserByUserName(newUser.getUserName()).isPresent()){
            throw new InvalidRequestException("Provided username is already in use");
        }
        newUser.setRole(Role.BASIC_USER);
        User registeredUser = userRepository.save(newUser);

        return registeredUser;
    }

    public boolean isUserValid(User userInQuestion){
        if (userInQuestion == null) {
            return false;
        }
        if (userInQuestion.getFirstName() == null || userInQuestion.getFirstName().trim().equals("")){
            return false;
        }
        if(userInQuestion.getLastName() == null || userInQuestion.getLastName().trim().equals("")){
            return false;
        }
        if(userInQuestion.getUserName() == null || userInQuestion.getUserName().trim().equals("")){
            return false;
        }
        if (userInQuestion.getPassWord() == null || userInQuestion.getPassWord().trim().equals("")){
            return false;
        }
        return true;
    }
}
