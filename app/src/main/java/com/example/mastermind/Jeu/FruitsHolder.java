package com.example.mastermind.Jeu;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mastermind.R;

public class FruitsHolder extends RecyclerView.ViewHolder {
    ImageView img_1,img_2,img_3,img_4;

    public FruitsHolder(@NonNull View itemView) {
        super(itemView);
        this.img_1 = itemView.findViewById(R.id.f1_img);
        this.img_2 = itemView.findViewById(R.id.f2_img);
        this.img_3 = itemView.findViewById(R.id.f3_img);
        this.img_4 = itemView.findViewById(R.id.f4_img);
    }
}
