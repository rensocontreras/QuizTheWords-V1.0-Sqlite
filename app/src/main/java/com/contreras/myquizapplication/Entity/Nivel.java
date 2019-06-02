package com.contreras.myquizapplication.Entity;

import java.io.Serializable;

public class Nivel implements Serializable {
    int numero_nivel;
    int tot_preguntas;
    int limite_preguntas;

    public int getNumero_nivel() {
        return numero_nivel;
    }

    public void setNumero_nivel(int numero_nivel) {
        this.numero_nivel = numero_nivel;
    }

    public int getTot_preguntas() {
        return tot_preguntas;
    }

    public void setTot_preguntas(int tot_preguntas) {
        this.tot_preguntas = tot_preguntas;
    }

    public int getLimite_preguntas() {
        return limite_preguntas;
    }

    public void setLimite_preguntas(int limite_preguntas) {
        this.limite_preguntas = limite_preguntas;
    }
}
