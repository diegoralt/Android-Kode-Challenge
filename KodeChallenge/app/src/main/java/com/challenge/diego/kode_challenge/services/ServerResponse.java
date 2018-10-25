package com.challenge.diego.kode_challenge.services;

import com.challenge.diego.kode_challenge.commons.Constants;

/**
 * Created by diego on 10/20/18.
 */

class ServerResponse {
    private Constants.CODERESPONSE estado;
    private String cadenaRespuesta;

    Constants.CODERESPONSE getEstado() {
        return estado;
    }

    void setEstado(Constants.CODERESPONSE estado) {
        this.estado = estado;
    }

    String getCadenaRespuesta() {
        return cadenaRespuesta;
    }

    void setCadenaRespuesta(String cadenaRespuesta) {
        this.cadenaRespuesta = cadenaRespuesta;
    }
}
