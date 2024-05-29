package com.politecnicomalaga.malakatrivialapp.control;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.politecnicomalaga.malakatrivialapp.control.GameRVAdapter;
import com.politecnicomalaga.malakatrivialapp.R;

public class GameViewHolder extends RecyclerView.ViewHolder {

    private TextView tvNombre, tvDescripcion;

    public GameViewHolder(@NonNull View itemView, GameRVAdapter adapter) {
        super(itemView);

        tvNombre = itemView.findViewById(R.id.tvNombre);
        tvDescripcion = itemView.findViewById(R.id.tvDescrip);

    }

    public TextView getTvNombre() {
        return tvNombre;
    }

    public void setTvNombre(TextView tvNombre) {
        this.tvNombre = tvNombre;
    }

    public TextView getTvDescripcion() {
        return tvDescripcion;
    }

    public void setTvDescripcion(TextView tvDescripcion) {
        this.tvDescripcion = tvDescripcion;
    }
}
