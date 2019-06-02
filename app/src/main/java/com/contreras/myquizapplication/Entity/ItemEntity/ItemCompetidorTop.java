package com.contreras.myquizapplication.Entity.ItemEntity;

public class ItemCompetidorTop {

    int codigo_usuario;
    String nombres;
    String sexo;
    int numero_nivel;
    int my_timer;
    int preguntas_resueltas;

    public int getCodigo_usuario() {
        return codigo_usuario;
    }

    public void setCodigo_usuario(int codigo_usuario) {
        this.codigo_usuario = codigo_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getNumero_nivel() {
        return numero_nivel;
    }

    public void setNumero_nivel(int numero_nivel) {
        this.numero_nivel = numero_nivel;
    }

    public int getMy_timer() {
        return my_timer;
    }

    public void setMy_timer(int my_timer) {
        this.my_timer = my_timer;
    }

    public int getPreguntas_resueltas() {
        return preguntas_resueltas;
    }

    public void setPreguntas_resueltas(int preguntas_resueltas) {
        this.preguntas_resueltas = preguntas_resueltas;
    }
}
