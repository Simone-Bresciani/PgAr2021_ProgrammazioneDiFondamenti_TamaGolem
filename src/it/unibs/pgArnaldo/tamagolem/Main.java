package it.unibs.pgArnaldo.tamagolem;

import mylib.InputDati;
import mylib.MyMenu;

public class Main {

    public static void main(String[] args) {

        int rivincita = 0; // variabile per far ripartire da capo lo scontro in caso si voglia rigiocare
        int numero_elementi
        do{
            MyMenu menu_difficolta = nuovoMenu("difficolta");
            menu_difficolta.stampaMenu();
            do{
                int numero_elementi = InputDati.leggiIntero(Costanti.SCELTA_DIFFICOLTA);
            }while(numero_elemnti ==0)

            Equilibrio matriceEquilibrio = new Equilibrio(numero_elementi);
            matriceEquilibrio.stampaMatriceEquilibrio();
            Corrispondenze prova = new Corrispondenze();
            System.out.println(prova.getLista_corrispondenze());

        }while(rivincita == 1);
        System.out.println(Costanti.END);
    }

    /**
     * Metodo per la creazione dei vari menu
     * @param funzione corrispondente alla tipologia del menu da creare
     * @return menu in base alla funzione specificata come parametro
     */
    private static MyMenu nuovoMenu(String funzione){
        switch (funzione){
            case "difficolta":
                String[] menu_d = new String[4];
                menu_d[0] = Costanti.EASY;
                menu_d[1] = Costanti.MEDIUM;
                menu_d[2] = Costanti.DIFFICULT;
                menu_d[3] = Costanti.GOD;
                MyMenu menu_difficolta = new MyMenu(Costanti.DIFFICOLTA,menu_d);
        }
    }


}
