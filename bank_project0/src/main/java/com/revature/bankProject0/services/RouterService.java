package com.revature.bankProject0.services;

import com.revature.bankProject0.exceptions.BadRouteRequestedException;
import com.revature.bankProject0.screens.HomeScreen;
import com.revature.bankProject0.screens.LoginScreen;
import com.revature.bankProject0.screens.RegisterScreen;
import com.revature.bankProject0.screens.Screen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RouterService {

    private Set<Screen> screens = new HashSet<>();

    public RouterService addScreen(Screen screen){
        LogService.log("Adding " + screen.getRoute() + "To screen set");
        screens.add(screen);
        return this;
    }

    public void route( String route){
        screens.stream()
                .filter(screen -> screen.getRoute().equals(route))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No screen found with that route."))
                .render();
    }

}
