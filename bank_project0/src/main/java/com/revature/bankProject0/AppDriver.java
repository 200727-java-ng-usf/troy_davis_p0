package com.revature.bankProject0;

import com.revature.bankProject0.models.ConsoleColors;
import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.screens.HomeScreen;
import com.revature.bankProject0.screens.LoginScreen;
import com.revature.bankProject0.screens.RegisterScreen;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.RouterService;
import com.revature.bankProject0.services.UserService;
import com.revature.bankProject0.util.AppState;

public class AppDriver {
    public static AppState app = new AppState();

    public static void main(String[] args) {

        while (app.isAppRunning()){
            app.getRouterService().route("/home");
        }
    }
}
