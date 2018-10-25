package com.challenge.diego.kode_challenge.model;

import com.challenge.diego.kode_challenge.commons.Constants;
import com.challenge.diego.kode_challenge.session.Session;
import com.challenge.diego.kode_challenge.utils.PrintConsole;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by diego on 10/24/18.
 */

public class MainParse {
    private Session session = Session.getInstance();

    public void initParse(Constants.OPERATION operation, String response) {
        switch (operation) {
            case saveDevice:
                parseSave(response);
                break;
            case getDevices:
                parseGetDevice(response);
                break;
        }
    }

    private void parseSave(String response) {
        try {
            JSONObject mainJson = new JSONObject(response);
            if (mainJson.has("datetime"))
                session.setDateCreation(Device.dateFormatter(mainJson.getString("datetime")));
        } catch (JSONException e) {
            PrintConsole.e("JSONException", e.getMessage());
        }
    }

    private void parseGetDevice(String response) {
        try {
            ArrayList<Device> list = new ArrayList<>();
            JSONObject mainJson = new JSONObject(response);
            JSONArray arrayDevice = mainJson.getJSONArray("objects");
            for (int i = 0; i < arrayDevice.length(); i++) {
                JSONObject jsonDevice = arrayDevice.getJSONObject(i);
                String name = jsonDevice.has("name") ? jsonDevice.getString("name") : "";
                String strength = jsonDevice.has("strength") ? jsonDevice.getString("strength") : "";
                String created = jsonDevice.has("created") ? jsonDevice.getString("created") : "";
                Device device = new Device(name, strength, created);
                list.add(device);
            }
            session.setListDevices(list);
        } catch (JSONException e) {
            PrintConsole.e("JSONException", e.getMessage());
        }
    }
}
