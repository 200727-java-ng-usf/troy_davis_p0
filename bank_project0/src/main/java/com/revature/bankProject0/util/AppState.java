package com.revature.bankProject0.util;

import com.revature.bankProject0.models.User;
import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.screens.DashboardScreen;
import com.revature.bankProject0.screens.HomeScreen;
import com.revature.bankProject0.screens.LoginScreen;
import com.revature.bankProject0.screens.RegisterScreen;
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
        LogService.log(" Initializing application");

        appRunning = true;
        //initialize the console
        console =  new BufferedReader(new InputStreamReader(System.in));

        final UserRepository userRepository = new UserRepository();

        final UserService userService = new UserService(userRepository);

        routerService = new RouterService();

        routerService.addScreen(new HomeScreen())
                    .addScreen(new LoginScreen(userService))
                    .addScreen(new RegisterScreen(userService))
                    .addScreen(new DashboardScreen());
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
}
