package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.models.TransactionType;
import com.revature.bankProject0.services.AccountService;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.TransactionService;
import com.revature.bankProject0.services.UserService;

import java.io.IOException;

import static com.revature.bankProject0.AppDriver.app;

public class CreateTransactionScreen extends Screen{
    private TransactionService transactionService;
    private AccountService accountService;
    private UserService userService;
    public CreateTransactionScreen(TransactionService transactionService,
                                   AccountService accountService,
                                   UserService userService){
        super("CreateTransactionScreen","/createTransaction");
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.userService = userService;
    }

    @Override
    public void render() {
        LogService.log("Rendering " + app.getCurrentUser().getFirstName() + "'s Create Transaction Screen...");
        String userSelection;

        System.out.println("What would you like to do?");
        System.out.println("1) Create Deposit");
        System.out.println("2) Create Withdrawal");
        System.out.println("?) back to Dash");

        try {
            System.out.println("Choose...");
            userSelection = app.getConsole().readLine();
            Transaction transaction = new Transaction();
            switch (userSelection){
                case "1":
                    transaction.setTransactionType(TransactionType.DEPOSIT);
                    break;
                case "2":
                    transaction.setTransactionType(TransactionType.WITHDRAWAL);
                    break;
                default:
                    app.getRouterService().route("/dash");
                    break;
            }
            transaction.setPrimaryAccountOwner(app.getCurrentUser().getId());
            System.out.println("Here are your account details:");
            System.out.println(app.getUserAccounts());
            System.out.println("Enter the account number that you would like to use: ");
            System.out.print("account number: ");
            transaction.setAccountNumber(Integer.valueOf(app.getConsole().readLine()));
            System.out.println("Enter the amount: ");
            transaction.setTransactionAmount(Double.valueOf(app.getConsole().readLine()));
//            transaction.setAccountBalance();
        } catch (IOException e) {
            LogService.logErr(e.toString());
            //kill the application
            app.setAppRunning(false);
        }

    }
}
