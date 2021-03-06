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
    @NonNull
    private long mTime;

    public Device(){}

    public Device(@NonNull final String name, @NonNull final String strength) {
        setmName(name);
        setmStrength(strength);
    }

    Device(@NonNull final String name, @NonNull final String strength, @NonNull final String dateCreation) {
        setmName(name);
        setmStrength(strength);
        setmDateCreation(dateFormatter(dateCreation));
        setmTime(getTime(dateCreation));
    }

    @NonNull
    public String getmName() {
        return mName;
    }

    private void setmName(@NonNull final String name) {
        this.mName = name;
    }

    @NonNull
    public String getmStrength() {
        return mStrength;
    }

    private void setmStrength(@NonNull final String strength) {
        this.mStrength = strength;
    }

    @NonNull
    public String getmDateCreation() {
        return mDateCreation;
    }

    private void setmDateCreation(@NonNull final String dateCreation) {
        this.mDateCreation = dateCreation;
    }

    @NonNull
    public long getmTime() {
        return mTime;
    }

    private void setmTime(@NonNull long mTime) {
        this.mTime = mTime;
    }

    public static String dateFormatter(String dateCreation) {
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

    public long getTime(String dateCreation) {
        long l = 0;
        try {
            Locale locale = new Locale("es", "MX");
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'", locale);
            Date date = inputFormat.parse(dateCreation);
            l = date.getTime();
        } catch (ParseException e) {
            PrintConsole.e("ParseException - dateFormatter", e.getMessage());
        }
        return l;
    }
}
