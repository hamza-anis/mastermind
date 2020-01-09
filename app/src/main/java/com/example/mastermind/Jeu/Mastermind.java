package com.example.mastermind.Jeu;

import java.util.ArrayList;
import java.util.Random;

public class Mastermind {
    private ArrayList<Fruit> allFruits;
    private ArrayList<Fruit> combinaison;
    public Mastermind() {
        this.allFruits = new ArrayList<>();
    }

    private void initAllFruits() {
        Fruit fraise = new Fruit("Fraise", false, false);
        Fruit banane = new Fruit("Banane", false, true);
        Fruit framboise = new Fruit("Framboise", false, false);
        Fruit kiwi = new Fruit("Kiwi", false, true);
        Fruit orange = new Fruit("Orange",false,true);
        Fruit prune = new Fruit("Prune",true,false);
        Fruit raisin = new Fruit("Raison",true,false);
        Fruit citron = new Fruit("Citron",true,true);
        this.allFruits.add(fraise);this.allFruits.add(banane);
        this.allFruits.add(framboise);this.allFruits.add(kiwi);
        this.allFruits.add(orange);this.allFruits.add(prune);
        this.allFruits.add(raisin);this.allFruits.add(citron);
    }

    private void createSecretCombinaison() {
        this.combinaison = new ArrayList<>();
        Random rd = new Random(this.allFruits.size()-1);
        for (int i = 0; i < 4; i++){
            Fruit f = this.allFruits.get(rd.nextInt());
            if (! this.combinaison.contains(f)){
                this.combinaison.add(f);
            }
        }
    }
}
