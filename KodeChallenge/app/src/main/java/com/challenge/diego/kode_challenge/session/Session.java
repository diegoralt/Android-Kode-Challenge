package com.challenge.diego.kode_challenge.session;

/**
 * Created by diego on 10/24/18.
 */

public class Session {
    private static Session theInstance = null;

    public static Session getInstance() {
        if (theInstance == null)
            theInstance = new Session();

        return theInstance;
    }

    private int currentWindow;

    public int getCurrentWindow() {
        return currentWindow;
    }

    public void setCurrentWindow(int currentWindow) {
        this.currentWindow = currentWindow;
    }
}
