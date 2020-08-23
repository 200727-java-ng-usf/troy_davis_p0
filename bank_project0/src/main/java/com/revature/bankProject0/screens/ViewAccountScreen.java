package com.revature.bankProject0.screens;

import com.revature.bankProject0.services.AccountService;
import com.revature.bankProject0.services.LogService;

import java.io.IOException;

import static com.revature.bankProject0.AppDriver.app;

public class ViewAccountScreen extends Screen {
    private AccountService accountService;
    public ViewAccountScreen(AccountService accountService){
        super("ViewAccountScreen", "/viewAccount");
        this.accountService = accountService;
    }

    @Override
    public void render() {
        LogService.log("Rendering " + app.getCurrentUser().getFirstName() + "'s Account...");
        String userSelection;
        accountService.getAccountsForUser(app.getCurrentUser());
        String accountView = app.getUserAccounts().toString();
        System.out.println(accountView);
        //TODO: Get user account balance
        System.out.println("1) Back to Dashboard");
        System.out.println("?) Sign Out");

        try {
            System.out.println("Choose...");
            userSelection = app.getConsole().readLine();

            switch (userSelection){
                case "1":
                    app.getRouterService().route("/dash");
                    break;
                default:
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
