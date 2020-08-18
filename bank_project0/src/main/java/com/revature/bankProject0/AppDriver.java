package com.revature.bankProject0;

import com.revature.bankProject0.repositories.UserRepository;
import com.revature.bankProject0.screens.HomeScreen;
import com.revature.bankProject0.screens.LoginScreen;
import com.revature.bankProject0.screens.RegisterScreen;
import com.revature.bankProject0.services.UserService;

public class AppDriver {
    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();

        UserService userService = new UserService(userRepository);

        HomeScreen homeScreen = new HomeScreen(userService);
        homeScreen.render();
        RegisterScreen registerScreen = new RegisterScreen(userService);

        registerScreen.render();

        LoginScreen loginScreen = new LoginScreen(userService);
        loginScreen.render();


    }
}
