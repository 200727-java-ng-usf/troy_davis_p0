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

    public HomeScreen(){
        super("HomeScreen", "/home");
    }


    @Override
    public void render() {

        try {
            System.out.println(
                "Welcome to Troys bank! "+
                "\n" + "1) to create a new account"  +
                "\n" + "2) to login: " +
                "\n" + "?) anything else to exit");
            String route = app.getConsole().readLine();
            if (route.equals("1")){
                app.getRouterService().route("/register");
            }else if (route.equals("2")){
                app.getRouterService().route("/login");
            }else {
                app.setAppRunning(false);
                System.out.println("Bye!");
            }
        } catch (IOException e) {
            LogService.logErr(e.toString());
        }
    }
}
