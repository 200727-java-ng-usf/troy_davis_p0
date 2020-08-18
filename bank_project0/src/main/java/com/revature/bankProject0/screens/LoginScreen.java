package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.User;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoginScreen extends Screen{
    private String name;
    private String route;

    //dependency to the log in screen
    private UserService userService;


    //inject the dependency through the constructor(constructor injection)
    public LoginScreen(UserService userService){
        LogService.log(" Instantiating: " + this.getClass().getName());

        //set the user service
        this.userService = userService;
    }


    @Override
    public String getRoute() {
        return this.route;
    }

    @Override
    public void render() {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String username;
        String password;

        try {
            System.out.println("Please provide login credentials");
            System.out.println("Username: ");
            username = console.readLine();
            System.out.println("Password: ");
            password = console.readLine();
            System.out.println("you entered: " + username + "/" + password);
            LogService.log("user: "+ username + " attempted to log in");

            User authorizedUser = userService.authenticate(username,password);



        } catch (IOException e) {
            e.printStackTrace();
            LogService.log(e.getStackTrace().toString());
        }
    }
}
