package com.revature.bankProject0.screens;

public abstract class Screen {
    /**
     * Displays a particular menu screen depending on the screen implementation
     */
    String name;
    public abstract String getRoute();
    public abstract void render();


}
