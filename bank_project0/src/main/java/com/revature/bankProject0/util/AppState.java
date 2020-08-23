package com.revature.bankProject0.util;

import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.screens.*;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.RouterService;
import com.revature.bankProject0.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {
    private BufferedReader console;
    private User currentUser;
    private RouterService routerService;
    private boolean appRunning;

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

        //add the screens to the router service
        routerService.addScreen(new HomeScreen())
                    .addScreen(new LoginScreen(userService))
                    .addScreen(new RegisterScreen(userService))
                    .addScreen(new DashboardScreen())
                    .addScreen(new ViewAccountScreen())
                    .addScreen(new ViewPastTransactionsScreen())
                    .addScreen(new CreateTransactionScreen())
                    .addScreen(new CreateBankAccountScreen());
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
}
