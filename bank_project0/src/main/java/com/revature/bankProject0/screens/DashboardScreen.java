package com.revature.bankProject0.screens;

import com.revature.bankProject0.services.LogService;

import java.io.IOException;

import static com.revature.bankProject0.AppDriver.app;

public class DashboardScreen extends Screen{
    /**
     * No-args constructor to set the Screen name and route designamtion
     */
    public DashboardScreen(){
        super("DashboardScreen", "/dash");
    }

    /**
     * Render Method
     *      -
     */
    @Override
    public void render() {
        String userSelection;
        LogService.log("Rendering " + app.getCurrentUser().getFirstName() + "'s Dashboard...");
        System.out.println("Rendering " + app.getCurrentUser().getFirstName() + "'s Dashboard...");

        //While loop to end if the user logs out
        while (app.isSessionValid()){
            System.out.println("1) View Accounts");
            System.out.println("2) View Past Transactions");
            System.out.println("3) Create New Transaction");
            System.out.println("4) Create New Bank Account");
            System.out.println("?) Sign Out");

            try {
                System.out.println("Choose...");
                userSelection = app.getConsole().readLine();

                //switch statement to move the user to requested screen
                switch (userSelection){
                    case "1":
                        app.getRouterService().route("/viewAccount");
                        break;
                    case "2":
                        app.getRouterService().route("/viewTransactions");
                        break;
                    case "3":
                        app.getRouterService().route("/createTransaction");
                        break;
                    case "4":
                        app.getRouterService().route("/createAccount");
                        break;
                    default:
                        LogService.log(app.getCurrentUser().getUserName() + " signing out...");
                        System.out.println(app.getCurrentUser().getUserName() + " signing out...");
                        app.invalidateCurrentSession();
                        break;
                }


            } catch (IOException e) {
                LogService.log(e.toString());
                //kill the application
                app.setAppRunning(false);
            }
        }
    }
}
