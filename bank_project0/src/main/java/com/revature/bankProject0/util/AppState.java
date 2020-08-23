package com.revature.bankProject0.util;

import com.revature.bankProject0.models.Account;
import com.revature.bankProject0.models.Transaction;
import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.AccountRepository;
import com.revature.bankProject0.repositories.TransactionRepository;
import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.screens.*;
import com.revature.bankProject0.services.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AppState {
    private BufferedReader console;
    private User currentUser;
    private RouterService routerService;
    private boolean appRunning;
    private Set<Account> userAccounts;
    private Set<Transaction> accountTransactions;

    public AppState(){
        new LogService();
        LogService.log("Initializing Application");
        //set the app to running
        appRunning = true;
        //initialize the console
        console =  new BufferedReader(new InputStreamReader(System.in));
        //create instance of user repository
        final UserRepository userRepository = new UserRepository();
        //create instance of user service
        final UserService userService = new UserService(userRepository);
        //create instance of new router service
        routerService = new RouterService();

        this.userAccounts = new HashSet<>();

        this.accountTransactions = new HashSet<>();

        final AccountRepository accountRepository = new AccountRepository();

        final AccountService accountService = new AccountService(accountRepository);

        final TransactionRepository transactionRepository = new TransactionRepository();

        final TransactionService transactionService = new TransactionService(transactionRepository);
        //add the screens to the router service
        routerService.addScreen(new HomeScreen())
                    .addScreen(new LoginScreen(userService))
                    .addScreen(new RegisterScreen(userService))
                    .addScreen(new DashboardScreen())
                    .addScreen(new ViewAccountScreen(accountService))
                    .addScreen(new ViewPastTransactionsScreen(transactionService))
                    .addScreen(new CreateTransactionScreen())
                    .addScreen(new CreateBankAccountScreen(accountService));
        LogService.log("Application initialization complete");


    }

    public BufferedReader getConsole() {
        return console;
    }

    public void setConsole(BufferedReader console) {
        this.console = console;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public RouterService getRouterService() {
        return routerService;
    }

    public void setRouterService(RouterService routerService) {
        this.routerService = routerService;
    }

    public boolean isAppRunning() {
        return appRunning;
    }

    public void setAppRunning(boolean appRunning) {
        this.appRunning = appRunning;
    }

    public boolean isSessionValid(){
        return (this.currentUser != null);
    }
    public void invalidateCurrentSession(){
        currentUser = null;
    }

    public Set<Account> getUserAccounts() {

        return userAccounts;
    }

    public void setUserAccounts(Set<Account> userAccounts) {
        this.userAccounts = userAccounts;
    }
    public void addToUserAccounts(Account userAccount){
        this.userAccounts.add(userAccount);
    }

    public Set<Transaction> getAccountTransactions() {
        return accountTransactions;
    }

    public void setAccountTransactions(Set<Transaction> accountTransactions) {
        this.accountTransactions = accountTransactions;
    }
    public void addToAccountTransactions(Set<Transaction> accountTransactions){
        this.accountTransactions.addAll(accountTransactions);
    }
}
