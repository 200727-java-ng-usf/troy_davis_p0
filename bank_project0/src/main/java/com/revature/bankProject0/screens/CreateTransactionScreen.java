package com.revature.bankProject0.screens;

import com.revature.bankProject0.models.ConsoleColors;
import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.models.TransactionType;
import com.revature.bankProject0.services.AccountService;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.TransactionService;
import com.revature.bankProject0.services.UserService;

import java.io.IOException;
import java.util.InputMismatchException;

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


        try {
            System.out.println("What would you like to do?");
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "1) Create Deposit" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "2) Create Withdrawal" + ConsoleColors.RESET);
            System.out.println("Choose...");
            //get the users input
            userSelection = app.getConsole().readLine();

            //create new Transaction
            Transaction transaction = new Transaction();
            transaction.setPrimaryAccountOwner(app.getCurrentUser().getId());
            System.out.println("Here are your account details:");
            accountService.getAccountsForUser(app.getCurrentUser());
            System.out.println(app.getUserAccounts());
            System.out.println();
            System.out.println("Enter the account number that you would like to use: ");
            System.out.print("account number: ");
            transaction.setAccountNumber(Integer.valueOf(app.getConsole().readLine()));
            transaction.setAccountBalance(app.getCurrentAccount(transaction
                                                .getAccountNumber())
                                                .getAccountBalance());
            System.out.println("Enter the amount: ");
            System.out.print("$");
            transaction.setTransactionAmount(Double.valueOf(app.getConsole().readLine()));
            switch (userSelection){
                case "1":
                    transaction.setTransactionType(TransactionType.DEPOSIT);
                    transaction.setEndingBalance(transaction.getAccountBalance() + transaction.getTransactionAmount());
                    transactionService.createDepositTransaction(transaction);
                    app.getRouterService().route("/dash");
                    break;
                case "2":
                    if (transaction.getTransactionAmount() > transaction.getAccountBalance()){
                        System.out.println("Sorry No Over-Drafting!");
                        app.getRouterService().route("/createTransaction");
                    }
                    transaction.setTransactionType(TransactionType.WITHDRAWAL);
                    transaction.setEndingBalance(transaction.getAccountBalance() - transaction.getTransactionAmount());
                    transactionService.createWithdrawalTransaction(transaction);
                    app.getRouterService().route("/dash");

                    break;
                default:
                    app.getRouterService().route("/dash");
                    break;
            }

        } catch (IOException | InputMismatchException | NullPointerException e) {
            LogService.logErr(e.toString());
            System.out.println("Something went wrong trying to create that transaction, please try again!");
            app.getRouterService().route("/dash");
        }catch (Exception e){
            LogService.logErr(e.toString());
            //kill the application
            app.setAppRunning(false);
        }


    }
}
