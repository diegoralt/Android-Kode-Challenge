package com.challenge.diego.kode_challenge.model;

import android.support.annotation.NonNull;

import com.challenge.diego.kode_challenge.utils.PrintConsole;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by diego on 10/20/18.
 */

public class Device {
    @NonNull
    private String mName;
    @NonNull
    private String mStrength;
    @NonNull
    private String mDateCreation;

    public Device(@NonNull final String name, @NonNull final String strength) {
        setmName(name);
        setmStrength(strength);
    }

    public Device(@NonNull final String name, @NonNull final String strength, @NonNull final  String dateCreation) {
        setmName(name);
        setmStrength(strength);
        setmDateCreation(dateFormatter(dateCreation));
    }

    @NonNull
    public String getmName() {
        return mName;
    }

    public void setmName(@NonNull final String name) {
        this.mName = name;
    }

    @NonNull
    public String getmStrength() {
        return mStrength;
    }

    public void setmStrength(@NonNull final String strength) {
        this.mStrength = strength;
    }

    @NonNull
    public String getmDateCreation() {
        return mDateCreation;
    }

    public void setmDateCreation(@NonNull final String dateCreation) {
        this.mDateCreation = dateCreation;
    }

    public String dateFormatter(String dateCreation) {
        String formattedDate = "";
        try {
            Locale locale = new Locale("es", "MX");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'", locale);
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMMM-yyyy hh:mm aaa", locale);
            Date date = inputFormat.parse(dateCreation);
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            PrintConsole.e("ParseException - dateFormatter", e.getMessage());
        }
        return formattedDate;
    }
}
