package com.example.mastermind.Jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mastermind {
    private HashMap<String,Fruit> allFruits;
    private ArrayList<Fruit> combinaison;
    public Mastermind() {
        this.allFruits = new HashMap<>();
        initAllFruits();
    }

    private void initAllFruits() {
        Fruit fraise = new Fruit("fraise", false, false);
        Fruit banane = new Fruit("banane", false, true);
        Fruit framboise = new Fruit("framboise", false, false);
        Fruit kiwi = new Fruit("kiwi", false, true);
        Fruit orange = new Fruit("orange",false,true);
        Fruit prune = new Fruit("prune",true,false);
        Fruit raisin = new Fruit("raisin",true,false);
        Fruit citron = new Fruit("citron",true,true);
        this.allFruits.put("fraise",fraise);this.allFruits.put("banane",banane);
        this.allFruits.put("framboise",framboise);this.allFruits.put("kiwi",kiwi);
        this.allFruits.put("orange",orange);this.allFruits.put("prune",prune);
        this.allFruits.put("raisin",raisin);this.allFruits.put("citron",citron);
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

    public HashMap<String,Fruit> getAllFruits() {
        return this.allFruits;
    }
    public Fruit getAFruit(String f){
        return this.allFruits.get(f);
    }
    public ArrayList<Fruit> getCombinaison() {
        return this.combinaison;
    }

}
