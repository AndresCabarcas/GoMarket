package com.testing.gomarket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class tiendasAdapter extends RecyclerView.Adapter<tiendasAdapter.ViewHolder> implements View.OnClickListener {
    List<Tienda> tiendas;
    private View.OnClickListener listener;
    public tiendasAdapter(List<Tienda> tiendas){
        this.tiendas = tiendas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tiendas, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.NIT.setText(tiendas.get(position).getNIT());
        holder.nombre.setText(tiendas.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return tiendas.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView NIT, nombre;
        ImageView eliminar;
        public ViewHolder(@NonNull View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.cardView);
            NIT = (TextView) view.findViewById(R.id.nitEmpresa);
            nombre = (TextView) view.findViewById(R.id.Nombre);
            eliminar = (ImageView) view.findViewById(R.id.eliminar);
        }
    }
}
