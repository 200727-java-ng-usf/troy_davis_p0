package com.revature.bankProject0.screens;

import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.TransactionService;

import java.io.IOException;

import static com.revature.bankProject0.AppDriver.app;

public class ViewPastTransactionsScreen extends Screen {

    private TransactionService transactionService;

    public ViewPastTransactionsScreen(TransactionService transactionService){
        super("ViewPastTransactionsScreen","/viewTransactions");
        this.transactionService = transactionService;
    }

    @Override
    public void render() {
        LogService.log("Rendering "
                        + app.getCurrentUser().getFirstName()
                        + "'s Past Transactions");
        String userSelection;
        System.out.println("Here are your transactions!");
        transactionService.getTransactionsForAccountAndUser(app.getCurrentUser(), app.getUserAccounts());
        String transactionsView = app.getAccountTransactions().toString();
        System.out.println(transactionsView);

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
            LogService.logErr(e.toString());
            //kill the application
            app.setAppRunning(false);
        }
    }
}
