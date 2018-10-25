package com.challenge.diego.kode_challenge.views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.challenge.diego.kode_challenge.R;
import com.challenge.diego.kode_challenge.commons.Constants;
import com.challenge.diego.kode_challenge.utils.PrintConsole;
import com.challenge.diego.kode_challenge.views.more_device.MoreDeviceView;
import com.challenge.diego.kode_challenge.views.search_device.SearchDeviceView;

/**
 * Created by diego on 10/23/18.
 */

public class BaseActivity extends AppCompatActivity {
    protected ProgressDialog mProgressDialog;
    private BaseActivity currentActivity;

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public void showScreen(int id) {
        Class<?> activity = null;
        switch (id) {
            case 1:
                activity = SearchDeviceView.class;
                break;
            case 2:
                activity = MoreDeviceView.class;
                break;
        }
        Intent intent = new Intent(currentActivity, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        currentActivity.startActivity(intent);
    }

    public void muestraIndicadorActividad(String strTitle, String strMessage) {
        if(strMessage.equalsIgnoreCase("cargando"))
            PrintConsole.e("app", "cargando");

        if(mProgressDialog != null)
            ocultaIndicadorActividad();

        try{
            mProgressDialog = ProgressDialog.show(this, strTitle,
                    strMessage, true);
            mProgressDialog.setCancelable(false);
        } catch(Exception ex) {
            PrintConsole.e("Exception", ex.getMessage());
        }
    }

    public void ocultaIndicadorActividad() {
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            try {
                mProgressDialog.dismiss();
            } catch(Exception ex) {
                PrintConsole.w("Exception",ex.getMessage());
            }
        }
    }

    public void alertSimple(String title, String msg, Constants.CODERESPONSE code) {
        int idImg = 0;
        switch (code) {
            case OK:
                idImg = R.drawable.ic_success;
                break;
            case ERROR:
                idImg = R.drawable.ic_alert;
                break;
        }

        new AlertDialog.Builder(currentActivity)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(idImg)
                .setCancelable(false)
                .show();
    }
}
