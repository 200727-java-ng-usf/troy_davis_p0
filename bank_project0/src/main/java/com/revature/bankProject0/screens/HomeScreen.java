package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.ConsoleColors;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.RouterService;
import com.revature.bankProject0.services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.revature.bankProject0.AppDriver.app;

public class HomeScreen extends Screen{

    private String name;
    private String route;

    //dependency to the user service
    private UserService userService;

    /**
     * inject the dependency through the constructor(constructor injection)
     * @param
     */
    public HomeScreen(){
        super("HomeScreen", "/home");
    }

    @Override
    public String getRoute() {
        return this.route;
    }

    @Override
    public void render() {

        String route;

        try {
            System.out.println(
                    ConsoleColors.GREEN_BOLD_BRIGHT +
                            "Welcome to Troys bank! "+
                            "\n" + "Enter 1 to create a new account"  +
                            "\n" + "Enter 2 to login: " +
                            "\n" + ConsoleColors.RED_BOLD_BRIGHT
                                 + "anything else to exit" + ConsoleColors.RESET);
            route = app.getConsole().readLine();
            if (route.equals("1")){
                app.getRouterService().route("/register");
            }else if (route.equals("2")){
                app.getRouterService().route("/login");
            }else {
                app.setAppRunning(false);
                System.out.println("Bye!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
