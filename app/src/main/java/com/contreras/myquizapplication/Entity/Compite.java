package com.contreras.myquizapplication.Entity;

import java.io.Serializable;

public class Compite implements Serializable {

    int codigo_usuario;
    int numero_nivel;
    int estado_candado;
    int preguntas_resueltas;
    int my_timer;

    public int getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public int getNumero_nivel() {
        return numero_nivel;
    }

    public void setNumero_nivel(int numero_nivel) {
        this.numero_nivel = numero_nivel;
    }

    public int getEstado_candado() {
        return estado_candado;
    }

    public void setEstado_candado(int estado_candado) {
        this.estado_candado = estado_candado;
    }

    public int getPreguntas_resueltas() {
        return preguntas_resueltas;
    }

    public void setPreguntas_resueltas(int preguntas_resueltas) {
        this.preguntas_resueltas = preguntas_resueltas;
    }

    public int getMy_timer() {
        return my_timer;
    }

    public void setMy_timer(int my_timer) {
        this.my_timer = my_timer;
    }
}
