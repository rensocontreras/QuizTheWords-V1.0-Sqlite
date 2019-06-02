package com.contreras.myquizapplication.Interfaces;

import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.Entity.Pregunta;

public class IQuiz {

    public interface IQuizView{
        void enviarParametrosQuiz();
        void obtenerQuiz();
        void mostrarQuiz(Pregunta pregunta);

        void obtenerResultado(String opcionSeleccionada);

        void mostrarRespuestaCorrecta();
        void mostrarRespuestaIncorrecta();
        void mostrarRespuestaDefinifiva();

        void mostrarGodActivity();
        void mostrarBadActivity();
        void mostrarTimeOutGoodActivity();
        void mostrarTimeOutBadActivity();

        void mostrarTimer(int numeroSecond);

        void salir();
    }

    public interface IQuizPrensenter{
        void solicitaParametrosQuiz(Nivel nivelActual, Compite competicionActual, int codigo);
        void solicitarQuiz();
        void obtenerResultadoQuiz(Pregunta pregunta);

        void solicitarRespuesta(String opcionSeleccionada);
        void obtenerRespuesta(int tipo);

        void obtenerCalificacion(int tipo);

        void obtenerNumeroSegundos(int numeroSecond);

        void solicitarCancelarTimer();
    }

    public interface IQuizModel{
        void actualizarConfiguracionQuiz(Nivel nivelActual, Compite competicionActual, int codigo);
        void elaborarQuiz();

        void analisaRespuesta(String opcionSeleccionada);

        void anularTimer();
    }

}
