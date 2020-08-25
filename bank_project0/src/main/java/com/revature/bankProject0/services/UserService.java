package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.AuthenticationException;
import com.revature.bankProject0.exceptions.InvalidRequestException;
import com.revature.bankProject0.models.Role;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;

import static com.revature.bankProject0.AppDriver.app;

public class UserService {


    private UserRepository userRepository;

    /**
     * Public constructor to create an instance of the service taking in a User Repository
     * @param repo
     */
    public UserService(UserRepository repo){
        //print method instantiation signature
        LogService.log("Instantiating " + this.getClass().toString());
        userRepository = repo;

    }

    /**
     * public void method to register new users into the database
     *      - validates user
     *      - checks if username is present in database
     *      - checks if email address is present in database
     *      - returns user to register screen if so
     *      - sets new users role to basic member
     *      - saves user to database
     *      - sets current user to the new user
     * @param newUser
     */
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

        Optional<User> existingUserEmail = userRepository.findUserByEmail(newUser.getEmail());
        if (existingUserEmail.isPresent()) {
            // TODO implement a custom ResourcePersistenceException
            throw new RuntimeException("Provided username is already in use!");
        }

        newUser.setRole(Role.BASIC_USER);
        userRepository.save(newUser);
        app.setCurrentUser(newUser);
    }

    /**
     * public boolean method to tell if a given user is valid based on given fields
     *      - checks first, last, username, password, email for null
     *      - checks first, last, username, password, email for empty strings
     *      - returns true if not
     * @param userInQuestion
     * @return
     */
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
        if (userInQuestion.getEmail() == null || userInQuestion.getEmail().trim().equals("")){
            return false;
        }
        return true;
    }

    /**
     * Public void method to validate based on provided credentials
     *      - checks for null and empty fields from username and password
     *      - uses the userRepository to findUsersByCredentials(username,password)
     *      - if authenticated, sets the current user to the authorized user
     *      - throws AuthenticationException if not
     * @param username
     * @param password
     * @return
     */
    public void authenticate(String username, String password){
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")){
            LogService.logErr("Invalid credential values provided");
            throw new InvalidRequestException("Invalid credential values provided");
        }
        User authUser = userRepository.findUsersByCredentials(username,password)
                                    .orElseThrow(AuthenticationException::new);

        app.setCurrentUser(authUser);
    }

    public Set<User> getAllUsers(){
        return userRepository.getAllUsers();
    }
    public Set<User> getUsersByRole(Role role){
        return userRepository.getUsersByRole(role);
    }
    public Optional<User> getUserById(int id){
        return userRepository.findUserByUserId(id);
    }
    public Optional<User> getUsersByUsername(String username){
        if (username == null){
            return null;
        }
        return userRepository.findUserByUserName(username);
    }
    public boolean deleteUserById(int id){
        return userRepository.deleteUserById(id);
    }

    public void update (User updatedUser){
        if (!isUserValid(updatedUser)){
            LogService.log("Invalid user fields provided during registration");
            throw new InvalidRequestException("Invalid user fields provided during registration");
        }

        Optional<User> existingUser = userRepository.findUserByUserName(updatedUser.getUserName());
        if (existingUser.isPresent()){
            System.out.println("Provided username is already in use!");
            app.getRouterService().route("/register");
        }

        Optional<User> existingUserEmail = userRepository.findUserByEmail(updatedUser.getEmail());
        if (existingUserEmail.isPresent()) {
            // TODO implement a custom ResourcePersistenceException
            throw new RuntimeException("Provided username is already in use!");
        }

        updatedUser.setRole(Role.BASIC_USER);
        userRepository.update(updatedUser);
        app.setCurrentUser(updatedUser);
    }


}
