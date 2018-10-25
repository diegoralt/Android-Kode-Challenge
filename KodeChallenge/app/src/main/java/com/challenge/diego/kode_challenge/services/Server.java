package com.challenge.diego.kode_challenge.services;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.challenge.diego.kode_challenge.commons.Constants;
import com.challenge.diego.kode_challenge.model.MainParse;
import com.challenge.diego.kode_challenge.session.Session;
import com.challenge.diego.kode_challenge.utils.PrintConsole;
import com.challenge.diego.kode_challenge.views.BaseActivity;

import java.util.Hashtable;

/**
 * Created by diego on 10/20/18.
 */

public class Server {
    private static Server theInstance = null;

    private static HttpInvoker httpInvoker;
    private static BaseActivity controller;
    private Session session = Session.getInstance();

    public static Server getInstance(BaseActivity controller) {
        if (theInstance == null)
            createInstance(controller);

        return theInstance;
    }

    private Server() {
        httpInvoker = new HttpInvoker();
    }

    private synchronized static void createInstance(BaseActivity controller) {
        if (theInstance == null)
            theInstance = new Server();

        Server.controller = controller;
    }

    public void doNetworkOperation(Constants.OPERATION operation, Hashtable<String, String> params) {
        controller.muestraIndicadorActividad("Operación en curso.", "Conectando con servidor remoto.");
        new Operation(operation, params).execute();
    }

    private class Operation extends AsyncTask<Void, Void, ServerResponse> {
        private Constants.OPERATION operation;
        private Hashtable<String, String> params;

        Operation(Constants.OPERATION operation, Hashtable<String, String> params) {
            this.operation = operation;
            this.params = params;
        }

        @Override
        protected ServerResponse doInBackground(Void... voids) {
            return httpInvoker.operationService(operation, params);
        }

        @Override
        protected void onPostExecute(ServerResponse serverResponse) {
            controller.ocultaIndicadorActividad();
            if (serverResponse != null) {
                if (serverResponse.getEstado() == Constants.CODERESPONSE.OK) {
                    new MainParse().initParse(operation, serverResponse.getCadenaRespuesta());
                    if (Session.getInstance().getCurrentWindow() == 1) {
                        String msg = "Dispositivo almacenado satisfactoriamente";
                        String date = TextUtils.isEmpty(session.getDateCreation()) ? msg + "." : msg + " el " + session.getDateCreation();
                        controller.alertSimple("Exito", date, serverResponse.getEstado());
                    } else
                        controller.showScreen(2);
                } else {
                    PrintConsole.e("Consumo fallido", "No response");
                    controller.alertSimple("Aviso","Por el momento el servicio no esta disponible.", serverResponse.getEstado());
                }
            } else {
                PrintConsole.e("Consumo fallido", "Respuesta nula");
                controller.alertSimple("Aviso","Por el momento el servicio no esta disponible.", serverResponse.getEstado());
            }
        }
    }
}
