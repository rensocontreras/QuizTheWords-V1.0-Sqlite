package com.contreras.myquizapplication.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.contreras.myquizapplication.Entity.ItemEntity.ItemCompetidorTop;
import com.contreras.myquizapplication.R;

import java.util.ArrayList;

public class CompetidorAdapter extends RecyclerView.Adapter<CompetidorAdapter.CompetidorAdapterViewHolder> {

    Context context;
    ArrayList<ItemCompetidorTop> competidoresTop;
    int item_competidor;

    public CompetidorAdapter(Context context, ArrayList<ItemCompetidorTop> competidoresTop, int item_competidor) {
        this.context = context;
        this.competidoresTop = competidoresTop;
        this.item_competidor = item_competidor;
    }

    @Override
    public CompetidorAdapterViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_competidor_top, parent, false);
        return new CompetidorAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CompetidorAdapterViewHolder holder, int i) {
        ItemCompetidorTop competidor = competidoresTop.get(i);

        holder.tv_nombre_usuario.setText(competidor.getNombres());
        holder.tv_numero_nivel.setText(competidor.getNumero_nivel()+"");
        holder.tv_preguntas_resueltas_top.setText(competidor.getPreguntas_resueltas()+"");
        holder.tv_my_timer.setText(competidor.getMy_timer()+"");

        if(competidor.getSexo().equals("masculino"))
            holder.iv_perfil.setImageResource(R.drawable.man);
        else
            holder.iv_perfil.setImageResource(R.drawable.woman);

    }

    @Override
    public int getItemCount() {
        return competidoresTop.size();
    }


    public class CompetidorAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nombre_usuario;
        TextView tv_numero_nivel;
        TextView tv_preguntas_resueltas_top;
        TextView tv_my_timer;
        ImageView iv_perfil;

        public CompetidorAdapterViewHolder(View itemView) {
            super(itemView);
            iv_perfil = itemView.findViewById(R.id.iv_perfil);
            tv_nombre_usuario = itemView.findViewById(R.id.tv_nombre_usuario);
            tv_numero_nivel = itemView.findViewById(R.id.tv_numero_nivel);
            tv_preguntas_resueltas_top = itemView.findViewById(R.id.tv_preguntas_resueltas_top);
            tv_my_timer = itemView.findViewById(R.id.tv_my_timer);
        }
    }
}
