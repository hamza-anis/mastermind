package com.example.mastermind.Jeu;

public class Fruit {
    private String nom;
    private boolean gotSeeds;
    private int drawable;
    private boolean isPeelable;
    private int state;
    public Fruit(int drawable,String nom, boolean gotSeeds, boolean isPeelable){
        this.state = 0;
        this.nom = nom;
        this.isPeelable = isPeelable;
        this.gotSeeds = gotSeeds;
        this.drawable = drawable;
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
    public int getDrawable(){
        return this.drawable;
    }

    public int getStateDrawable() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
