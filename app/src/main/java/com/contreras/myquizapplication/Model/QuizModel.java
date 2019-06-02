package com.contreras.myquizapplication.Model;

import android.os.Handler;

import com.contreras.myquizapplication.Database.MyOpenHelper;
import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.Entity.Pregunta;
import com.contreras.myquizapplication.Entity.Top;
import com.contreras.myquizapplication.Interfaces.IQuiz;
import com.contreras.myquizapplication.Presenter.QuizPresenter;

import com.contreras.myquizapplication.View.QuizActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QuizModel implements IQuiz.IQuizModel {

    private QuizPresenter presenter;

    MyOpenHelper db;
    Nivel nivelActual;
    Compite competicionActual;
    int codigo;


    ArrayList<Pregunta> list_preguntas;
    int posicionCorriente;
    int preguntasResueltas;


    int numSecond=60;
    TimerTask task;



    public QuizModel(QuizPresenter presenter, QuizActivity view){
        this.presenter = presenter;
        db = new MyOpenHelper(view);
        posicionCorriente = 0;
        preguntasResueltas = 0;
    }


    @Override
    public void actualizarConfiguracionQuiz(Nivel nivelActual, Compite competicionActual, int codigo) {
            this.nivelActual = nivelActual;
            this.competicionActual = competicionActual;
            this.codigo = codigo;
            list_preguntas = db.obtenerPreguntas(nivelActual.getNumero_nivel());

        //Start timer
        myTimerStart();

    }

    @Override
    public void elaborarQuiz() {
        Pregunta myPregunta = (Pregunta) list_preguntas.get(posicionCorriente);
        presenter.obtenerResultadoQuiz(myPregunta);
    }

    @Override
    public void analisaRespuesta(String opcionSeleccionada) {
        if(opcionSeleccionada.equals(list_preguntas.get(posicionCorriente).getRespuesta())){
            //Actualizar el numero de Preguntas del objeto nivel actual
            preguntasResueltas++;
            presenter.obtenerRespuesta(1);
        }else{
            presenter.obtenerRespuesta(2);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if((posicionCorriente+1)<list_preguntas.size()){
                    //Actualizo position dell'arrayPreguntas y seteo il Quiz
                    posicionCorriente++;
                    Pregunta myPregunta = (Pregunta) list_preguntas.get(posicionCorriente);
                    presenter.obtenerResultadoQuiz(myPregunta);
                }else {
                    controlFinalRespuesta();
                }
            }
        },600);
    }


    public void controlFinalRespuesta(){
        if (preguntasResueltas >= nivelActual.getLimite_preguntas()) {
            //Toast.makeText(this, "Nivel completado", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.obtenerCalificacion(1);
                }
            }, 700);

            if (db.existenciaNivelSiguiente(nivelActual.getNumero_nivel() + 1)) {
                //TODO, ARREGLAR NIVEL SIGUIENTE
                db.actualizarCompeticionSiguiente(competicionActual.getCodigo_usuario(), nivelActual.getNumero_nivel() + 1, 1);//Nivel siguiente unlock
            } else {
                presenter.obtenerRespuesta(3);
            }
        } else {
            //Toast.makeText(this, "Intentalo otra vez!", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.obtenerCalificacion(2);
                }
            }, 700);
        }

        if (preguntasResueltas >= competicionActual.getPreguntas_resueltas()) {
            competicionActual.setPreguntas_resueltas(preguntasResueltas);
            if (competicionActual.getMy_timer() >= (60 - numSecond)) {
                competicionActual.setMy_timer(60 - numSecond);
                db.actualizarTimer(competicionActual.getCodigo_usuario(), nivelActual.getNumero_nivel(), competicionActual.getMy_timer());
            }
        }

        actualizarTop();

        db.actualizarCompeticionActual(competicionActual.getCodigo_usuario(), nivelActual.getNumero_nivel(), competicionActual.getPreguntas_resueltas());

        anularTimer();
    }

    private void actualizarTop() {

        Top nuevoCompetidorTop = new Top();
        nuevoCompetidorTop.setNumero_nivel(nivelActual.getNumero_nivel());
        nuevoCompetidorTop.setCodigo_usuario(codigo);
        nuevoCompetidorTop.setMy_timer(competicionActual.getMy_timer());
        nuevoCompetidorTop.setPreguntas_resueltas(competicionActual.getPreguntas_resueltas());

        Top competidorTop = db.obtenerCompetidorTop(nivelActual.getNumero_nivel());

        if(competidorTop!=null){
            if(competidorTop.getPreguntas_resueltas()<=competicionActual.getPreguntas_resueltas()) {
                if (competidorTop.getMy_timer() >= competicionActual.getMy_timer()) {
                    db.actualizarCompetidorTop(nuevoCompetidorTop);
                }
            }
        }else{
            db.insertarCompetidor(nuevoCompetidorTop);
        }
    }


    public void controlFinalRespuestaTimeOut(){
        if (preguntasResueltas >= nivelActual.getLimite_preguntas()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.obtenerCalificacion(3);

                }
            }, 700);

            if (db.existenciaNivelSiguiente(nivelActual.getNumero_nivel() + 1)) {
                //TODO, ARREGLAR NIVEL SIGUIENTE
                db.actualizarCompeticionSiguiente(competicionActual.getCodigo_usuario(), nivelActual.getNumero_nivel() + 1, 1);//Nivel siguiente unlock
            }
        } else {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                    presenter.obtenerCalificacion(4);

        }

        if (preguntasResueltas >= competicionActual.getPreguntas_resueltas()) {
            competicionActual.setPreguntas_resueltas(preguntasResueltas);
            if (competicionActual.getMy_timer() >= (60 - numSecond)) {
                competicionActual.setMy_timer(60 - numSecond);
                db.actualizarTimer(competicionActual.getCodigo_usuario(), nivelActual.getNumero_nivel(), competicionActual.getMy_timer());
            }
        }

        actualizarTop();

        db.actualizarCompeticionActual(competicionActual.getCodigo_usuario(), nivelActual.getNumero_nivel(), competicionActual.getPreguntas_resueltas());

        anularTimer();
    }


    private void myTimerStart() {
        final Timer timer = new Timer();
        task = new TimerTask() {
            public void run() {
                        presenter.obtenerNumeroSegundos(numSecond);
                        numSecond--;
                        if (numSecond == -1) {

                            try {
                                Thread.sleep(700);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            numSecond=0;
                            controlFinalRespuestaTimeOut();


                            timer.cancel();
                        }
            }

        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


    @Override
    public void anularTimer() {
        task.cancel();
    }

}
