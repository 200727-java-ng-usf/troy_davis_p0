package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.ConsoleColors;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.bankProject0.AppDriver.app;

/**
 * Screen to register new Users
 */
public class RegisterScreen extends Screen {
    private UserService userService;


    public RegisterScreen(UserService userService){
        super("RegisterScreen","/register");
        //only good for development
        LogService.log("Instantiating " + this.getClass().getName());
        //set the userService upon Instantiation
        this.userService = userService;

    }

    @Override
    public void render() {
        String firstName, lastName, userName, password;

        try {

            System.out.println("Welcome! sign up for a new account!");
            System.out.println("First Name: ");
            firstName = app.getConsole().readLine();
            System.out.println("Last Name: ");
            lastName = app.getConsole().readLine();
            System.out.println("userName: ");
            userName = app.getConsole().readLine();
            System.out.println("Password: ");
            password = app.getConsole().readLine();

            //create new User instance based on given values

            User newUser = new User(firstName,lastName,userName,password);
            LogService.log("new user attempted to sign up: " + "\n" + firstName + "," + lastName + "," + userName + "," + password);

            userService.register(newUser);

            if (app.isSessionValid()){
                System.out.println("Congrats! here are your new account details: " + newUser);
                app.getRouterService().route("/dash");
            }


        } catch (IOException e) {
            e.printStackTrace();
            app.setAppRunning(false);
        }
    }
}
