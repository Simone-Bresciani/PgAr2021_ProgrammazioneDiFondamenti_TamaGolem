package it.unibs.pgArnaldo.tamagolem;

import java.util.ArrayList;

public class Tamagolem {

    //attributi
    private ArrayList<String> pietre_utilizzate;
    private int vita;
    private boolean esausto;

    //costruttore
    public Tamagolem(ArrayList<String> pietre_utilizzate, int vita, boolean esausto) {
        this.pietre_utilizzate = pietre_utilizzate;
        this.vita = vita;
        this.esausto = esausto;
    }

    //get
    public ArrayList<String> getPietre_utilizzate() {
        return pietre_utilizzate;
    }

    public int getVita() {
        return vita;
    }

    public boolean isEsausto() {
        return esausto;
    }

    //set
    public void setPietre_utilizzate(ArrayList<String> pietre_utilizzate) { this.pietre_utilizzate = pietre_utilizzate; }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public void setEsausto(boolean esausto) {
        this.esausto = esausto;
    }

}
