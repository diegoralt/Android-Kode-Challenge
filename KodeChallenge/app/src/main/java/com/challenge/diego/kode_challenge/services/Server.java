package com.challenge.diego.kode_challenge.services;

import android.content.Context;
import android.os.AsyncTask;

import com.challenge.diego.kode_challenge.commons.Constants;
import com.challenge.diego.kode_challenge.utils.PrintConsole;

import java.util.Hashtable;

/**
 * Created by diego on 10/20/18.
 */

public class Server {
    private static Server theInstance = null;

    private static HttpInvoker httpInvoker;
    private static Context controller;

    public static Server getInstance(Context controller) {
        if (theInstance == null)
            createInstance(controller);

        return theInstance;
    }

    private Server() {
        httpInvoker = new HttpInvoker();
    }

    private synchronized static void createInstance(Context controller) {
        if (theInstance == null)
            theInstance = new Server();

        Server.controller = controller;
    }

    public void doNetworkOperation(Constants.OPERATION operation, Hashtable<String, String> params) {
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
            ServerResponse response = new ServerResponse();
            response = httpInvoker.operationService(operation, params);

            return response;
        }

        @Override
        protected void onPostExecute(ServerResponse serverResponse) {
            if (serverResponse != null) {
                if (serverResponse.getEstado() == Constants.CODERESPONSE.OK) {

                } else {
                    PrintConsole.e("Consumo fallido", serverResponse.getCadenaRespuesta());
                }
            } else {
                PrintConsole.e("Consumo fallido", "Respuesta nula");
            }
        }
    }
}
