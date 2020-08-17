package com.revature.bankProject0.repositories;

import com.revature.bankProject0.db.UserDB;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.services.LogService;

import java.util.Optional;

public class UserRepository {



    public UserRepository(){
        LogService.log("Instantiating " + this.getClass().toString());
    }

    private UserDB userDataset = UserDB.userDataSet;

    public Optional<User> findUsersByCredentials(String userName, String password){
        return userDataset.fndUserByCredentials(userName, password);
    }

    public Optional<User> findUserByUserName(String username){
        return userDataset.findUserByUserName(username);
    }

    public User save(User newUser){
        return userDataset.addUser(newUser);
    }
}
