package com.contreras.myquizapplication.Interfaces;

import com.contreras.myquizapplication.Entity.ItemEntity.ItemCompetidorTop;
import java.util.ArrayList;

public class IRanking {

    public interface IRankingView{
        void obtenerCompetidoresTop();
        void mostrarListaCompetidoresTop(ArrayList<ItemCompetidorTop> competidoresTop);
    }

    public interface IRankingPresenter{
        void solicitarListaCompetidoresTop();
        void obtenerListaCompetidoresTop(ArrayList<ItemCompetidorTop> competidoresTop);
    }

    public interface IRankingmodel{
        void consultaListaCompetidoresTop();
    }

}
