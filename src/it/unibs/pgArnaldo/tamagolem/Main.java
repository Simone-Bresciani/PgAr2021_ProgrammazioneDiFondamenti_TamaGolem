package it.unibs.pgArnaldo.tamagolem;

import mylib.InputDati;

public class Main {

    public static void main(String[] args) {

        int rivincita = 0; // variabile per far ripartire da capo lo scontro in caso si voglia rigiocare
        do{
            int livello = InputDati.leggiIntero(Costanti.LIVELLO);

        }while(rivincita == 1);
        System.out.println(Costanti.END);
    }
}
