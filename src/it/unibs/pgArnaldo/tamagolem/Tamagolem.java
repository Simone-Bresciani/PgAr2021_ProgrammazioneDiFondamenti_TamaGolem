package it.unibs.pgArnaldo.tamagolem;

public class Tamagolem {

    //attributi
    private int[] pietre_utilizzate;
    private int vita;
    private boolean esausto;

    //costruttore
    public Tamagolem(int[] pietre_utilizzate, int vita, boolean esausto) {
        this.pietre_utilizzate = pietre_utilizzate;
        this.vita = vita;
        this.esausto = esausto;
    }

    //get
    public int[] getPietre_utilizzate() {
        return pietre_utilizzate;
    }

    public int getVita() {
        return vita;
    }

    public boolean isEsausto() {
        return esausto;
    }

    //set
    public void setPietre_utilizzate(int[] pietre_utilizzate) {
        this.pietre_utilizzate = pietre_utilizzate;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public void setEsausto(boolean esausto) {
        this.esausto = esausto;
    }
}
