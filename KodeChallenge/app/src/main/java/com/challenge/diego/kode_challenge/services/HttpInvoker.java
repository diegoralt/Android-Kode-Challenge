package com.challenge.diego.kode_challenge.services;

import com.challenge.diego.kode_challenge.commons.Constants;
import com.challenge.diego.kode_challenge.utils.PrintConsole;

import org.json.JSONObject;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by diego on 10/20/18.
 */

public class HttpInvoker {
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    HttpInvoker() {
        createClient();
    }

    private void createClient(){
        try {
            client = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .build();
        } catch (Exception e) {
            PrintConsole.e("Exception", e.getMessage());
        }
    }

    ServerResponse operationService(Constants.OPERATION operation, Hashtable<String, String> params) {
        switch (operation) {
            case getDevices:
                return getDevices();
            case saveDevice:
                return saveDevice(params);
        }
        return null;
    }

    private ServerResponse getDevices() {
        ServerResponse serverResponse = new ServerResponse();
        try {
            String url = Constants.URL_METHOD_GET;
            PrintConsole.i("URL", url);

            Request request = new Request.Builder()
                    .url(url).build();

            Response response = client.newCall(request).execute();
            serverResponse = getResponse(response);
        } catch (Exception e) {
            PrintConsole.e("Exception - getDevice", e.getMessage());
        }
        return serverResponse;
    }

    private ServerResponse saveDevice(Hashtable<String, String> params) {
        ServerResponse serverResponse = new ServerResponse();
        try {
            String url = Constants.URL_METHOD_POST;
            PrintConsole.i("URL", url);

            JSONObject object = new JSONObject();
            object.put("name", params.get(Constants.KEY_PARAM_NAME));
            object.put("strength", params.get(Constants.KEY_PARAM_STRENGTH));
            PrintConsole.i("Payload", object.toString());

            RequestBody body = RequestBody.create(JSON, object.toString());

            Request request = new Request.Builder()
                    .url(url)
                    .post(body).build();

            Response response = client.newCall(request).execute();
            serverResponse = getResponse(response);
        } catch (Exception e) {
            PrintConsole.e("Exception - saveDevice", e.getMessage());
        }
        return serverResponse;
    }

    private ServerResponse getResponse(Response response) throws Exception{
        ServerResponse serverResponse = new ServerResponse();
        String result = response.body().string();
        if (response.code() == 200 || response.code() == 204)
            serverResponse.setEstado(Constants.CODERESPONSE.OK);
        else
            serverResponse.setEstado(Constants.CODERESPONSE.ERROR);

        serverResponse.setCadenaRespuesta(result);

        return serverResponse;
    }
}
