package com.example.mastermind.Jeu;

public class Fruit {
    private String nom;
    private boolean gotSeeds;
    private boolean isPeelable;

    public Fruit(String nom, boolean gotSeeds, boolean isPeelable){
        this.nom = nom;
        this.isPeelable = isPeelable;
        this.gotSeeds = gotSeeds;
    }

    public String getNom() {
        return nom;
    }

    public boolean isGotSeeds() {
        return gotSeeds;
    }

    public boolean isPeelable() {
        return isPeelable;
    }
    public String getDrawable(){
        return this.nom+".png";
    }
}
