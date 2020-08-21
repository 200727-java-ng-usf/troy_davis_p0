package com.revature.bankProject0.screens;

import static com.revature.bankProject0.AppDriver.app;

public class DashboardScreen extends Screen{
    public DashboardScreen(){
        super("DashboardScreen", "/dash");
    }

    @Override
    public void render() {
        System.out.println("welcome to your Dashboard!" + app.getCurrentUser().getFirstName());
    }
}
