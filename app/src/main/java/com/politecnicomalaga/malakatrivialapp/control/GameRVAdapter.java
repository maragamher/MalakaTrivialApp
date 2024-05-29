package com.politecnicomalaga.malakatrivialapp.control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.politecnicomalaga.malakatrivialapp.R;

import java.util.List;

public class GameRVAdapter extends RecyclerView.Adapter<GameViewHolder> {
    private LayoutInflater inflater;
    private List<String[]> nosotros;

    public GameRVAdapter(Context ct, List<String[]> nosotros) {
        this.nosotros = nosotros;
        inflater = LayoutInflater.from(ct);
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = inflater.inflate(R.layout.rv_main, parent, false);

        return new GameViewHolder(viewItem, this);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        String[] persona = this.nosotros.get(position);

        holder.getTvNombre().setText(persona[0]);
        holder.getTvDescripcion().setText(persona[1]);

    }

    @Override
    public int getItemCount() {
        return nosotros.size();
    }
}
