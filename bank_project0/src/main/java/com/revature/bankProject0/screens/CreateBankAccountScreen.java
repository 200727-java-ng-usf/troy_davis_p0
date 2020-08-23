package com.revature.bankProject0.screens;

import com.revature.bankProject0.services.LogService;

import java.io.IOException;

import static com.revature.bankProject0.AppDriver.app;

public class CreateBankAccountScreen extends Screen{

    public CreateBankAccountScreen(){
        super("CreateBankAccountScreen","/createAccount");
    }

    @Override
    public void render() {
        LogService.log("Rendering " + app.getCurrentUser().getFirstName() + "'s Create Account Screen...");
        String userSelection;
        System.out.println("Create Bank Account Page Under Construction!");
        //TODO: Create UserAccount
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
            LogService.log(e.getStackTrace());
            //kill the application
            app.setAppRunning(false);
        }
    }
}
