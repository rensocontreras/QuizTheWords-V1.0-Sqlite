package com.contreras.myquizapplication.Entity;

public class Pregunta {

    int numero_pregunta;
    int numero_nivel;
    String contenido_pregunta;
    String opcion1;
    String opcion2;
    String opcion3;
    String opcion4;
    String respuesta;

    public int getNumero_pregunta() {
        return numero_pregunta;
    }

    public void setNumero_pregunta(int numero_pregunta) {
        this.numero_pregunta = numero_pregunta;
    }

    public int getNumero_nivel() {
        return numero_nivel;
    }

    public void setNumero_nivel(int numero_nivel) {
        this.numero_nivel = numero_nivel;
    }

    public String getContenido_pregunta() {
        return contenido_pregunta;
    }

    public void setContenido_pregunta(String contenido_pregunta) {
        this.contenido_pregunta = contenido_pregunta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
