package com.challenge.diego.kode_challenge.commons;

/**
 * Created by diego on 10/20/18.
 */

public class Constants {
    public final static String URL_METHOD_GET = "http://mock.westcentralus.cloudapp.azure.com/grin_test/bluetooth/all?order=1";
    public final static String URL_METHOD_POST = "http://mock.westcentralus.cloudapp.azure.com/grin_test/bluetooth/create";

    public enum CODERESPONSE{
        OK,
        ERROR
    }

    public enum OPERATION {
        getDevices,
        saveDevice
    }

    public final static String KEY_PARAM_NAME = "keyName";
    public final static String KEY_PARAM_STRENGTH = "keyStrength";
}
