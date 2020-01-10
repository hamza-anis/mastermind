package com.example.mastermind.Jeu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mastermind.R;

import java.util.ArrayList;

public class FruitsAdapter extends RecyclerView.Adapter<FruitsHolder> {
    ArrayList<Fruit> selection;
    public FruitsAdapter(ArrayList<Fruit> selection){
    this.selection =  selection;
    }
    @NonNull
    @Override
    public FruitsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.historique,parent,false);
        return new FruitsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitsHolder holder, int position) {
        holder.img_1.setImageResource(R.drawable.orange);
        holder.img_2.setImageResource(R.drawable.fraise);
    }

    @Override
    public int getItemCount() {
        return selection.size();
    }
}
