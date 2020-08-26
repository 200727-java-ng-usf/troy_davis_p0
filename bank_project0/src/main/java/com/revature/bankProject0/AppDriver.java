package com.revature.bankProject0;

import com.revature.bankProject0.util.AppState;

public class AppDriver {
    public static AppState app = new AppState();

    public static void main(String[] args) {

        while (app.isAppRunning()){
            app.getRouterService().route("/home");
        }
    }
}
