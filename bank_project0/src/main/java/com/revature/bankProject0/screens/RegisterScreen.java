package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.User;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Screen to register new Users
 */
public class RegisterScreen  extends Screen {

    private String name;
    private String route;
    private UserService userService;


    public RegisterScreen(UserService userService){
        //only good for development
        LogService.log("Instantiating " + this.getClass().getName());
        //set the userService upon Instantiation
        this.userService = userService;
        this.name = "RegisterScreen";
        this.route = "/register";

    }

    @Override
    public void getRoute() {

    }

    @Override
    public void render() {
        String firstName, lastName, userName, password;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Welcome! sign up for a new account!");
            System.out.println("First Name: ");
            firstName = console.readLine();
            System.out.println("Last Name: ");
            lastName = console.readLine();
            System.out.println("userName: ");
            userName = console.readLine();
            System.out.println("Password: ");
            password = console.readLine();

            //create new User instance based on given values

            User newUser = new User(firstName,lastName,userName,password);

            User registeredUser = userService.register(newUser);
            System.out.println("Congrats! here are your new account details: " + registeredUser);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
