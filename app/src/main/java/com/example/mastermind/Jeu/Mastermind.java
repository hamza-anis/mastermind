package com.example.mastermind.Jeu;

import com.example.mastermind.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Mastermind {
    private HashMap<String,Fruit> allFruits;
    private ArrayList<Fruit> secretcombinaison;
    public Mastermind() {
        this.allFruits = new HashMap<>();
        initAllFruits();
        createSecretCombinaison();
    }

    private void initAllFruits() {
        Fruit fraise = new Fruit(R.drawable.fraise,"fraise", false, false);
        Fruit banane = new Fruit(R.drawable.banane,"banane", false, true);
        Fruit framboise = new Fruit(R.drawable.framboise,"framboise", false, false);
        Fruit kiwi = new Fruit(R.drawable.kiwi,"kiwi", false, true);
        Fruit orange = new Fruit(R.drawable.orange,"orange",false,true);
        Fruit prune = new Fruit(R.drawable.prune,"prune",true,false);
        Fruit raisin = new Fruit(R.drawable.raisin,"raisin",true,false);
        Fruit citron = new Fruit(R.drawable.citron,"citron",true,true);
        this.allFruits.put("fraise",fraise);this.allFruits.put("banane",banane);
        this.allFruits.put("framboise",framboise);this.allFruits.put("kiwi",kiwi);
        this.allFruits.put("orange",orange);this.allFruits.put("prune",prune);
        this.allFruits.put("raisin",raisin);this.allFruits.put("citron",citron);
    }

    private void createSecretCombinaison() {
        this.secretcombinaison = new ArrayList<>();
        Random rd = new Random();

        while (this.secretcombinaison.size() < 4){
            Fruit f = fruitsToArrayList().get(rd.nextInt(8));
            if (! this.secretcombinaison.contains(f)){
                this.secretcombinaison.add(f);
            }
        }
    }

    public HashMap<String,Fruit> getAllFruits() {
        return this.allFruits;
    }
    public Fruit getAFruit(String f){
        return this.allFruits.get(f);
    }
    public ArrayList<Fruit> fruitsToArrayList(){
        ArrayList<Fruit> res = new ArrayList<>();
        for(Fruit f : this.allFruits.values()){
            res.add(f);
        }
        return res;
    }
    public ArrayList<Fruit> getSecretcombinaison() {
        return this.secretcombinaison;
    }


}
