package com.challenge.diego.kode_challenge.services;

import com.challenge.diego.kode_challenge.commons.Constants;

/**
 * Created by diego on 10/20/18.
 */

public class ServerResponse {
    private Constants.CODERESPONSE estado;
    private String cadenaRespuesta;

    public Constants.CODERESPONSE getEstado() {
        return estado;
    }

    public void setEstado(Constants.CODERESPONSE estado) {
        this.estado = estado;
    }

    public String getCadenaRespuesta() {
        return cadenaRespuesta;
    }

    public void setCadenaRespuesta(String cadenaRespuesta) {
        this.cadenaRespuesta = cadenaRespuesta;
    }
}
