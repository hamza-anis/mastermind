package com.example.mastermind.Jeu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mastermind.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.fViewHolder> {
    private ArrayList<Selection> historique;
    public static class fViewHolder extends RecyclerView.ViewHolder{
        ImageView img1;
        ImageView img2;
        ImageView img3;
        ImageView img4;

        public fViewHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.img_1);
            img2 = itemView.findViewById(R.id.img_2);
            img3 = itemView.findViewById(R.id.img_3);
            img4 = itemView.findViewById(R.id.img_4);
        }
    }
    public Adapter(ArrayList<Selection> historique){
        this.historique = historique;

    }
    @NonNull
    @Override
    public fViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.historique,parent,false);
        return new fViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull fViewHolder holder, int position) {
        Selection current = historique.get(position);
        holder.img1.setImageResource(current.getSelection().get(0).getDrawable());
        holder.img2.setImageResource(current.getSelection().get(1).getDrawable());
        holder.img3.setImageResource(current.getSelection().get(2).getDrawable());
        holder.img4.setImageResource(current.getSelection().get(3).getDrawable());
    }

    @Override
    public int getItemCount() {
        return historique.size();
    }


}
