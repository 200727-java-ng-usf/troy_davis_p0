package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.User;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.revature.bankProject0.AppDriver.app;

public class LoginScreen extends Screen{
    //dependency to the log in screen
    private UserService userService;


    //inject the dependency through the constructor(constructor injection)
    public LoginScreen(UserService userService){
        super("LoginScreen","/login");
        LogService.log(" Instantiating: " + this.getClass().getName());

        //set the user service
        this.userService = userService;
    }

    @Override
    public void render() {

        String username;
        String password;

        try {
            System.out.println("Please provide login credentials");
            System.out.println("Username: ");
            username = app.getConsole().readLine();
            System.out.println("Password: ");
            password = app.getConsole().readLine();
            System.out.println("you entered: " + username + "/" + password);
            LogService.log("user: "+ username + " attempted to log in");

            userService.authenticate(username,password);

            if (app.isSessionValid()){
                app.getRouterService().route("/dash");
            }

        } catch (IOException e) {
            LogService.log(Arrays.toString(e.getStackTrace()));
        }
    }
}
