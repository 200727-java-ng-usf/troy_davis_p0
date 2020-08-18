package com.revature.bankProject0.screens;

import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeScreen extends Screen{

    private String name;
    private String route;

    //dependency to the user service
    private UserService userService;

    /**
     * inject the dependency through the constructor(constructor injection)
     * @param userService
     */
    public HomeScreen(UserService userService){
        LogService.log(" Instantiating: " + this.getClass().getName());

        this.userService = userService;
    }

    @Override
    public String getRoute() {
        return this.route;
    }

    @Override
    public void render() {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        String route;

        try {
            System.out.println("Welcome to Troys bank! Enter 1 to create a new account" +
                                "\n"+" or Enter 2 to login: " + "\n" + ("or anything else to exit"));
            route = console.readLine();
            if (route.equals("1")){
                //TODO: ROUTE TO NEW ACCOUNT
            }else if (route.equals("2")){
                //TODO: ROUTE TO LOGIN
            }else {
                System.out.println("Bye!");
                LogService.log(" User decided to close aaplication");
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
