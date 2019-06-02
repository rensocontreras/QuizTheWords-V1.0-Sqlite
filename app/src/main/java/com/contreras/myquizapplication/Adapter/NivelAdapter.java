package com.contreras.myquizapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.contreras.myquizapplication.Entity.Compite;
import com.contreras.myquizapplication.Entity.Nivel;
import com.contreras.myquizapplication.View.QuizActivity;
import com.contreras.myquizapplication.R;
import com.contreras.myquizapplication.Util.Constantes;

import java.util.ArrayList;

public class NivelAdapter extends RecyclerView.Adapter<NivelAdapter.NivelAdapterViewHolder> {

    Context context;
    ArrayList<Nivel> niveles;
    ArrayList<Compite> competiciones;
    int id_nivel;

    public NivelAdapter(Context context, ArrayList<Nivel> niveles, ArrayList<Compite> competiciones, int id_nivel){
        this.context = context;
        this.niveles = niveles;
        this.competiciones = competiciones;
        this.id_nivel = id_nivel;
    }

    @Override
    public NivelAdapterViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nivel, parent, false);
        return new NivelAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NivelAdapterViewHolder holder, int i) {
        final Nivel nivelActual = niveles.get(i);
        final Compite competicionActual = competiciones.get(i);


        holder.tv_nivel.setText(nivelActual.getNumero_nivel()+"");
        holder.tv_estado_preguntas.setText(competicionActual.getPreguntas_resueltas()+"/"+nivelActual.getTot_preguntas());

        if(competicionActual.getEstado_candado()==1){
            holder.iv_candado.setImageResource(R.drawable.ic_lock_open);
        }else{
            holder.iv_candado.setImageResource(R.drawable.ic_lock_close);
        }

        holder.contendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(competicionActual.getEstado_candado()==1){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constantes.OBJ_NIVEL_ACTUAL,nivelActual);
                    bundle.putSerializable(Constantes.OBJ_COMPETICION_ACTUAL,competicionActual);
                    Intent i = new Intent(context, QuizActivity.class);
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
                else{
                    Toast.makeText(context, "Nivel Bloqueado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return niveles.size();
    }


    public class NivelAdapterViewHolder extends RecyclerView.ViewHolder {

        LinearLayout contendor;
        TextView tv_nivel;
        ImageView iv_candado;
        TextView tv_estado_preguntas;

        public NivelAdapterViewHolder(View itemView) {
            super(itemView);

            tv_nivel = itemView.findViewById(R.id.tv_nivel);
            iv_candado = itemView.findViewById(R.id.iv_candado);
            tv_estado_preguntas = itemView.findViewById(R.id.tv_estado_preguntas);
            contendor = itemView.findViewById(R.id.contenedor);
        }
    }

}
