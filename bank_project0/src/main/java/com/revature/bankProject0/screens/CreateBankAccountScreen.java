package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.AccountType;
import com.revature.bankProject0.services.AccountService;
import com.revature.bankProject0.services.LogService;

import java.io.IOException;

import static com.revature.bankProject0.AppDriver.app;

public class CreateBankAccountScreen extends Screen{
    private AccountService accountService;

    public CreateBankAccountScreen(AccountService accountService){
        super("CreateBankAccountScreen","/createAccount");
        this.accountService = accountService;
    }

    @Override
    public void render() {
        LogService.log("Rendering " + app.getCurrentUser().getFirstName() + "'s Create Account Screen...");
        String accountTypeSelection;
        System.out.println("Create A new Bank Account!");

        try {
            Account newAccount = new Account();
            newAccount.setPrimaryOwner(app.getCurrentUser().getId());
            newAccount.setSecondaryOwner(0);
            newAccount.setAccountBalance(0d);
            System.out.println("Choose Account Type: ");
            System.out.println("1) Checking Account");
            System.out.println("2) Savings Account");
            System.out.println("?) Back to Dashboard");
            System.out.println("Choose...");
            accountTypeSelection = app.getConsole().readLine();
            switch (accountTypeSelection){
                case "1":
                    newAccount.setAccountType(AccountType.CHECKING);
                    break;
                case "2":
                    newAccount.setAccountType(AccountType.SAVINGS);
                    break;
                default:
                    app.getRouterService().route("/dash");
                    break;
            }

            accountService.register(newAccount);

            if (app.isSessionValid()){
                System.out.println("Congrats here are your new account details: " + newAccount.toString());
                app.getRouterService().route("/dash");
            }

        } catch (IOException e) {
            LogService.log(e.toString());
            //kill the application
            app.setAppRunning(false);
        }
    }
}
