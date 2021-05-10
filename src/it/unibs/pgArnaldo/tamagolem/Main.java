package it.unibs.pgArnaldo.tamagolem;

import mylib.InputDati;
import mylib.MyMenu;

public class Main {

    public static void main(String[] args) {

        int rivincita = 0; // variabile per far ripartire da capo lo scontro in caso si voglia rigiocare
        do{
            //creo una lista con delle associazioni tra un indice e il nome degli elemennti
            //ne creo 9, poi in base alla difficoltà scelta, ne utilizzerò o tutte o una parte di esse
            Corrispondenze lista_corrispondenze = new Corrispondenze();
            //creo il menu per mostrare i livelli di difficoltà
            MyMenu menu_difficolta = nuovoMenu("difficolta");
            //stampo il menu
            menu_difficolta.stampaMenu();
            //in base alla difficolta scelta, imposterò il nuemero della tipologia degli elementi disponibili
            int numero_elementi = calcolaElementi(InputDati.leggiIntero(Costanti.SCELTA_DIFFICOLTA));
            //in base al numero degli elementi genero l'equilibrio
            Equilibrio matriceEquilibrio = new Equilibrio(numero_elementi);

        }while(rivincita == 1);
        System.out.println(Costanti.END);
    }

    /**
     *
     * @param difficolta_scelta range tra 1 e 4
     * @return il numero della tipoligia delle pietre disponibili in base alla difficoltà (tra 3 e 9)
     */
    private static int calcolaElementi(int difficolta_scelta){
        switch (difficolta_scelta){
            case 1:
                return 3;
            case 2:
                return 5;
            case 3:
                return 7;
            case 4:
                return 9;
        }
        return -1;
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
                return menu_difficolta;
            default: break;
        }
        return null;
    }


}
