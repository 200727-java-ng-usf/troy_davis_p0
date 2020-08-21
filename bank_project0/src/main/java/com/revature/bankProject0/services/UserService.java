package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.AuthenticationException;
import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Role;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static com.revature.bankProject0.AppDriver.app;

public class UserService {
    //print method instantiation signiture

    private UserRepository userRepository;

    public UserService(UserRepository repo){

        LogService.log("Instantiating " + this.getClass().toString());
        userRepository = repo;

    }

    public void register(User newUser){
        if (!isUserValid(newUser)){
            LogService.log("Invalid user fields provided during registration");
            throw new InvalidRequestException("Invalid user fields provided during registration");
        }

        Optional<User> existingUser = userRepository.findUserByUserName(newUser.getUserName());

        if (existingUser.isPresent()){
            System.out.println("Provided username is already in use!");
            app.getRouterService().route("/register");
        }

        newUser.setRole(Role.BASIC_USER);
        userRepository.save(newUser);

        app.setCurrentUser(newUser);
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

    /**
     * Validate based on provided credentials
     * @param username
     * @param password
     * @return
     */
    public void authenticate(String username, String password){
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            throw new InvalidRequestException("Invalid credential values provided");
        }
        User authUser = userRepository.findUsersByCredentials(username,password)
                                    .orElseThrow(AuthenticationException::new);

        app.setCurrentUser(authUser);
    }

    public Set<User> getAllUsers(){
        return new HashSet<>();
    }
    public Set<User> getUsersByRole(){
        return new HashSet<>();
    }
    public User getUserById(int id){
        return null;
    }
    public User getUsersByUsername(String username){
        return null;
    }
    public boolean deleteUserById(int id){
        return false;
    }
    public boolean update (User updatedUser){
        return false;
    }


}
