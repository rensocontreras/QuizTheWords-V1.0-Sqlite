package com.contreras.myquizapplication.Presenter;

import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.Entity.Pregunta;
import com.contreras.myquizapplication.Interfaces.IQuiz;
import com.contreras.myquizapplication.Model.QuizModel;
import com.contreras.myquizapplication.View.QuizActivity;

public class QuizPresenter implements IQuiz.IQuizPrensenter {

    private QuizActivity view;
    private QuizModel model;

    public QuizPresenter(QuizActivity view) {
        this.view  = view;
        model = new QuizModel(this,view);
    }

    @Override
    public void solicitaParametrosQuiz(Nivel nivelActual, Compite competicionActual, int codigo) {
        model.actualizarConfiguracionQuiz(nivelActual,competicionActual,codigo);
    }

    @Override
    public void solicitarQuiz() {
        model.elaborarQuiz();
    }

    @Override
    public void obtenerResultadoQuiz(Pregunta pregunta) {
        view.mostrarQuiz(pregunta);
    }

    @Override
    public void solicitarRespuesta(String opcionSeleccionada) {
        model.analisaRespuesta(opcionSeleccionada);
    }

    @Override
    public void obtenerRespuesta(int tipo) {
        if(tipo == 1)
            view.mostrarRespuestaCorrecta();

        if(tipo==2)
            view.mostrarRespuestaIncorrecta();

        if(tipo==3)
            view.mostrarRespuestaDefinifiva();
    }

    @Override
    public void obtenerCalificacion(int tipo) {
        if(tipo == 1)
            view.mostrarGodActivity();

        if(tipo == 2)
            view.mostrarBadActivity();

        if(tipo == 3)
            view.mostrarTimeOutGoodActivity();

        if(tipo == 4)
            view.mostrarTimeOutBadActivity();

    }

    @Override
    public void obtenerNumeroSegundos(int numeroSecond) {
        view.mostrarTimer(numeroSecond);
    }

    @Override
    public void solicitarCancelarTimer() {
        model.anularTimer();
    }


}
