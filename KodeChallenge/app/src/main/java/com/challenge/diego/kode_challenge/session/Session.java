package com.challenge.diego.kode_challenge.session;

import com.challenge.diego.kode_challenge.model.Device;

import java.util.ArrayList;

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
    private String dateCreation;
    private ArrayList<Device> listDevices = new ArrayList<>();

    public int getCurrentWindow() {
        return currentWindow;
    }

    public void setCurrentWindow(int currentWindow) {
        this.currentWindow = currentWindow;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ArrayList<Device> getListDevices() {
        return listDevices;
    }

    public void setListDevices(ArrayList<Device> listDevices) {
        this.listDevices = listDevices;
    }
}
