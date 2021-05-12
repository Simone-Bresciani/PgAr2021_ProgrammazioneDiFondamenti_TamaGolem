package it.unibs.pgArnaldo.tamagolem;

import mylib.InputDati;
import mylib.MyMenu;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int rivincita = 0; // variabile per far ripartire da capo lo scontro in caso si voglia rigiocare
        do{
            //creo una lista con delle associazioni tra un indice e il nome degli elementi
            //ne creo 9, poi in base alla difficoltà scelta, ne utilizzerò o tutte o una parte di esse
            Corrispondenze lista_corrispondenze = new Corrispondenze();
            //creo il menu per mostrare i livelli di difficoltà
            MyMenu menu_difficolta = nuovoMenu("difficolta");
            //stampo il menu
            menu_difficolta.stampaMenuSenzaUscita();
            //in base alla difficolta scelta, imposterò il nuemero della tipologia degli elementi disponibili
            int numero_elementi = calcolaElementi(InputDati.leggiIntero(Costanti.SCELTA_DIFFICOLTA));
            //in base al numero degli elementi genero l'equilibrio
            Equilibrio equilibrio = new Equilibrio(numero_elementi);

            //calcola il numero di pietre a disposizione per ogni golem
            int numero_pietre_per_golem =(int) Math.ceil((numero_elementi + 1)/3) +1;
            //calcola il numero di tamagolem a disposizione per ogni squadra, data la difficoltà
            int numero_tamagolem = (int) Math.ceil((double)(numero_elementi-1)*(double)(numero_elementi-2)/((double)2*numero_pietre_per_golem));
            //calcola il numero di pietre a disposizione nella scorta
            int numero_pietre_scorta =(int) Math.ceil((2 * (double)numero_tamagolem * (double)numero_pietre_per_golem)/(double)numero_elementi) * numero_elementi;
            //calcola la vita massima dei tamagolem
            int vita_massima = equilibrio.getVita();
            //inzio gioco: creo i 2 giocatori
            Squadra squadra1 = creaSquadra(numero_pietre_per_golem, numero_tamagolem, numero_pietre_scorta, numero_elementi, lista_corrispondenze, vita_massima);
            Squadra squadra2 = creaSquadra(numero_pietre_per_golem, numero_tamagolem, numero_pietre_scorta, numero_elementi, lista_corrispondenze, vita_massima);
            //creo lo scontro, impostando che la partita non è conclusa
            Scontro scontro = new Scontro(squadra1, squadra2, false);
            //inzio scontro
            System.out.println();
            System.out.println(Costanti.INIZIO_SCONTRO);
            System.out.println();

            //assegnazione preliminare delle pietre per i primi 2 tamagolem
            //System.out.println(Costanti.TURNO_1);
            //InputDati.leggiStringa("...digita per continuare...\n");
            assegnaPietre(numero_pietre_per_golem, squadra1, numero_elementi, Costanti.GIOCATORE1);
            assegnaPietre(numero_pietre_per_golem, squadra2, numero_elementi, Costanti.GIOCATORE2);


            //ciclo fino a battaglia conclusa che itera l'assegnazione di nuove pietre
            do{
                int risultato_battaglia = scontro.lancia(squadra1.getLista_tamagolem().get(0), squadra2.getLista_tamagolem().get(0), lista_corrispondenze);
                switch(risultato_battaglia) {
                    case 1://faccio ritornare 1 se il giocatore 1 ha perso il suo tamagolem per cui dovrá inserire le sue pietre
                        squadra1.getLista_tamagolem().remove(0);
                        //nuove pietre per nuovo tamagolem
                        assegnaPietre(numero_pietre_per_golem, squadra1, numero_elementi, Costanti.GIOCATORE1);
                        System.out.println(Costanti.RIPRESA_BATTAGLIA);
                        break;
                    case 2: //faccio ritornare 2 se si tratta del giocatore 2
                        squadra2.getLista_tamagolem().remove(0);
                        assegnaPietre(numero_pietre_per_golem, squadra2, numero_elementi, Costanti.GIOCATORE2);
                        System.out.println(Costanti.RIPRESA_BATTAGLIA);
                        break;
                    case 3://faccio ritornare 3 se entrambi hanno perso i tamagolem e dovrenno riassegnare le pietre entrambi
                        squadra1.getLista_tamagolem().remove(0);
                        squadra2.getLista_tamagolem().remove(0);
                        assegnaPietre(numero_pietre_per_golem, squadra1, numero_elementi, Costanti.GIOCATORE1);
                        assegnaPietre(numero_pietre_per_golem, squadra2, numero_elementi, Costanti.GIOCATORE2);
                        System.out.println(Costanti.RIPRESA_BATTAGLIA);
                        break;
                }
                    if(squadra1.getLista_tamagolem().size() == 0 && squadra2.getLista_tamagolem().size() == 0){
                        System.out.println(Costanti.PAREGGIO);
                        scontro.setConclusa(true);
                    }else if (squadra2.getLista_tamagolem().size()==0){
                        scontro.setVincitore(squadra1);
                        scontro.setConclusa(true);
                        System.out.println(Costanti.VINCITA + Costanti.GIOCATORE1);
                    }else if (squadra1.getLista_tamagolem().size()==0) {
                        scontro.setVincitore(squadra2);
                        scontro.setConclusa(true);
                        System.out.println(Costanti.VINCITA + Costanti.GIOCATORE2);
                    }
            }while(!scontro.isConclusa());
            rivincita = InputDati.leggiIntero(Costanti.RIVINCITA);
            //uscirà dallo scontro quando vede che la partita è conclusa
        }while(rivincita == 1);
        System.out.println(Costanti.END);
    }

    /**
     * <h3>metodo per il calcolo del numero della tipologia di elementi di pietre disponibili, data la difficoltà scelta</h3>
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
     * <h3>Metodo per la creazione dei vari menu</h3>
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

    /**
     * <h3>Metodo per la creazione di una squdra</h3>
     * @param numero_pietre_per_golem numero di pietre a disposizione per un tamagolem
     * @param numero_tamagolem numero dei tamagolem per una squdra
     * @param numero_pietre_scorta numero delle pietre nella scorta di una squadra
     * @param numero_elementi numero delle tipologia delle pietre nel gioco
     * @param lista_corrispondenze rapresenta la llista con le corrispondenze chiave->nome pitre
     * @param vita_massima rappresenta la vita dei tamagolem
     * @return la squadra creata
     */
    private static Squadra creaSquadra(int numero_pietre_per_golem, int numero_tamagolem, int numero_pietre_scorta, int numero_elementi, Corrispondenze lista_corrispondenze, int vita_massima ){
        //creo n tamagolem per la squdra, impostando solo la loro vita e se sono esausti
        ArrayList<Tamagolem> lista_tamagolem= new ArrayList<Tamagolem>();
        //creo i tamagolem e l'aggiungo all'arrayList dei tamagolem disponibili per la squadra
        for(int i=0; i<numero_tamagolem; i++){
            Tamagolem tamagolem = creaTamagolem(numero_pietre_per_golem, vita_massima);
            lista_tamagolem.add(tamagolem);
        }
        //creo un Arraylist di pietre che rappresentano la scorta
        ArrayList<String> lista_pietre = new ArrayList<String>();
        //ciclo il totale per ogni tipologia di pietra
        for (int i=0; i<numero_elementi; i++){
            //per ogni tipologia di pietra, inserisco nell'arrayList il nome della pietra ripetuto tante volte quanto il numero delle pietre/numero degli elemnti
            for(int j=0; j<(numero_pietre_scorta/numero_elementi); j++){
                //aggiungo alla mia lista il nome della pietra
                lista_pietre.add(lista_corrispondenze.getNome(i));
            }
        }
        Squadra nuova_squadra = new Squadra(lista_tamagolem, lista_pietre);
        nuova_squadra.getLista_pietre(numero_elementi);
        return nuova_squadra;
    }

    /**
     * <h3>metodo che crea l'oggetto tamagolem che inizializiamo con un array di pietre inizialmente nulle</h3>
     * @param numero_pietre_per_golem rappresenta il numero della tipologia di pietre a disposizione per un tamagolem
     * @param vita_inziale rappresenta
     * @return
     */
    private static Tamagolem creaTamagolem(int numero_pietre_per_golem, int vita_inziale){
        //creo un arraylist di pietre
        ArrayList<String> pietre_utilizzate = new ArrayList<String>();
        for(int i=0; i<numero_pietre_per_golem; i++){
            pietre_utilizzate.add(null);
        }
        //imposto di default che non è esausto
        Tamagolem tamagolem = new Tamagolem(pietre_utilizzate, vita_inziale, false);
        return tamagolem;
    }

    private static void assegnaPietre(int numero_pietre_per_golem, Squadra squadra, int numero_elementi, String giocatore){
        switch (giocatore) {
            case Costanti.GIOCATORE1:
                System.out.printf(Costanti.GIOCATORE1 + Costanti.NUMERO_TAMAGOLEM + "\n", squadra.getLista_tamagolem().size());
                System.out.printf(Costanti.GIOCATORE1 + Costanti.SCEGLI_PIETRE + "\n", numero_pietre_per_golem);
                break;
            case Costanti.GIOCATORE2:
                System.out.printf(Costanti.GIOCATORE2 + Costanti.NUMERO_TAMAGOLEM + "\n", squadra.getLista_tamagolem().size());
                System.out.printf(Costanti.GIOCATORE2 + Costanti.SCEGLI_PIETRE + "\n", numero_pietre_per_golem);
                break;
        }
        for(int i=1; i<= numero_pietre_per_golem; i++){
            System.out.println();
            System.out.println(squadra.getLista_pietre(numero_elementi));
            String lettura;
            do {
                System.out.printf(Costanti.INSERISCI_PIETRA, i);
                lettura = InputDati.leggiStringaNonVuota("");
            }while(!squadra.togliPietre(lettura));
            squadra.getLista_tamagolem().get(0).getPietre_utilizzate().add(lettura);
        }
    }
}
