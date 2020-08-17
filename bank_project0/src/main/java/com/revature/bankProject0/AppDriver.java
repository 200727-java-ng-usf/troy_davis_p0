package com.revature.bankProject0;

import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.screens.LoginScreen;
import com.revature.bankProject0.screens.RegisterScreen;
import com.revature.bankProject0.services.LogService;
import com.revature.bankProject0.services.UserService;

public class AppDriver {
    public static void main(String[] args) {
        LogService logService = new LogService();
        UserRepository userRepository = new UserRepository();

        UserService userService = new UserService(userRepository);

        RegisterScreen registerScreen = new RegisterScreen(userService);

        registerScreen.render();

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.render();
    }
}
