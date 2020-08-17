package com.revature.bankProject0.db;

import com.revature.bankProject0.models.Role;
import com.revature.bankProject0.models.User;

import java.util.HashMap;
import java.util.Optional;

public class UserDB  extends HashMap<Integer, User> {
    public static UserDB userDataSet = new UserDB();
    public static int key = 1;

    static {
        userDataSet.addUser(new User("troy","davis","admin","admin"));
    }
    public User addUser(User newUser){
        //copy the app user to avoid contaminating the original
        User nUser = new User(newUser);
        nUser.setId(key);
        userDataSet.put(key++, nUser);
        return nUser;
    }

    public Optional<User> fndUserByCredentials(String username, String password){
        return userDataSet.values()
                            .stream()
                            .filter(user -> user.getUserName().equals(username) && user.getPassWord().equals(password))
                            .findFirst();
    }

    public Optional<User> findUserByUserName(String username){
        return userDataSet.values()
                            .stream()
                            .filter(user -> user.getUserName().equals(username))
                            .findFirst();
    }
}
