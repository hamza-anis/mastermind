package com.example.mastermind.Jeu;

import java.util.ArrayList;

public class Selection {
    private Fruit f1,f2,f3,f4;
    private ArrayList<Fruit> selection = new ArrayList<>();
    public Selection(Fruit f1,Fruit f2,Fruit f3,Fruit f4){
        selection.add(f1);
        selection.add(f2);
        selection.add(f3);
        selection.add(f4);


    }
    public ArrayList<Fruit> getSelection(){
        return this.selection;
    }

}
