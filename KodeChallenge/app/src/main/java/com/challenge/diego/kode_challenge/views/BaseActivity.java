package com.challenge.diego.kode_challenge.views;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.challenge.diego.kode_challenge.R;
import com.challenge.diego.kode_challenge.utils.PrintConsole;

/**
 * Created by diego on 10/23/18.
 */

public class BaseActivity extends AppCompatActivity {
    protected ProgressDialog mProgressDialog;
    private BaseActivity currentActivity;

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.currentActivity = currentActivity;
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

    public void alertSimple(String msg) {
        new AlertDialog.Builder(this)
                .setTitle("Aviso")
                .setMessage("Es necesario que actives tu bluetooth")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.drawable.ic_alert)
                .setCancelable(false)
                .show();
    }
}
