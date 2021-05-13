package it.unibs.pgArnaldo.tamagolem;

import javax.print.DocFlavor;

public class Scontro {

    //attributi
    private Squadra squadra1;
    private Squadra squadra2;
    private Squadra vincitore;
    private boolean conclusa;

    //costruttore (non passo il vincitore perchè verrà settato tramite il metodo setVincitore)
    public Scontro(Squadra squadra1, Squadra squadra2, boolean conclusa) {
        this.squadra1 = squadra1;
        this.squadra2 = squadra2;
        this.conclusa = conclusa;
    }

    //get
    public Squadra getSquadra1() {
        return squadra1;
    }

    public Squadra getSquadra2() {
        return squadra2;
    }

    public Squadra getVincitore() {
        return vincitore;
    }

    public boolean isConclusa() {
        return conclusa;
    }

    //set
    public void setSquadra1(Squadra squadra1) {
        this.squadra1 = squadra1;
    }

    public void setSquadra2(Squadra squadra2) {
        this.squadra2 = squadra2;
    }

    public void setVincitore(Squadra vincitore) {
        this.vincitore = vincitore;
    }

    public void setConclusa(boolean conclusa) {
        this.conclusa = conclusa;
    }

    /**
     * <h3>Metodo per stabilire quale tamagolem vince sull'altro</h3>
     * @param tamgolem1 rarappresenta il tamagolem della squadra1ppresenta il tamagolem della squadra1
     * @param tamagolem2 rappresenta il tamagolem della squadra1
     * @param corrispondenze rappresenta le liste con i nomi delle pietre ed i corrispondenti indici
     * @param equilibrio ovvero la matrice delle adiacenze
     * @return l'esito della partita 1-giocatore 1 perde tamagolem 2-giocatore 2 perde tamagolem 3-entrambi perdono il proprio tamagolem
     */
    public int lancia(Tamagolem tamgolem1, Tamagolem tamagolem2, Corrispondenze corrispondenze, Equilibrio equilibrio){
        int vita1 = tamgolem1.getVita();
        int vita2 = tamagolem2.getVita();
        int[][] mat = equilibrio.getMat();

        for(int i=0; vita1>0 && vita2>0 ; i++) {
            //controllo che i sia arrivata alla finr dell'array, per poi far ripartire l'array dalla prima pietra
            if (i == tamgolem1.getPietre_utilizzate().size()) {
                i = 0;
            }
            //seleziono il nome della pietra dall'array delle pietre del tamagolem in posizione i
            /*in base al nome della pietra risalgo alla sua riga (per giocatore 1) o sua colonna (per giocatore2)
             all'interno della map corrispondenze, in particolare sfruttando la map lista_corrispondenze_reverse
             che ha come key il nome della pietra e come value l'indice a cui corrisponde*/
            Integer indice_pietra1 = corrispondenze.getKey(tamgolem1.getPietre_utilizzate().get(i)); //riga
            Integer indice_pietra2 = corrispondenze.getKey(tamagolem2.getPietre_utilizzate().get(i)); //colonna

            /* se il valore nella matrice in riga: indice_pietra1 e colonna: incide_pietra2 ha un valore minore di zero
               allora vuol dire che il tamagolem 1 sta subendo danno, quindi decrememnto la sua vita, ovvero aggiungo alla sua
               vita il valore(che è negativo)
               in caso questo valore sia positivo, vuol dire che fa danno, quindi decremento il valore della vita del tamagolem 2
             */
            if (mat[indice_pietra1][indice_pietra2] < 0) {
                vita1 += mat[indice_pietra1][indice_pietra2];
            } else if (mat[indice_pietra1][indice_pietra2] == 0) {
                //scalo la vita di uno a tutti e due, perchè in questo caso indice_pietra1=indice_pietra2
                vita1 -= 1;
                vita2 -= 1;
            } else if (mat[indice_pietra1][indice_pietra2] > 0) {
                vita2 -= mat[indice_pietra1][indice_pietra2];
            }
        }
        tamgolem1.setVita(vita1);
        tamagolem2.setVita(vita2);
        System.out.println();
        if(vita1 <= 0 && vita2 <= 0){
            return 3;
        }else if(vita1 <= 0){
            return  1;
        }else
            return 2;
    }
}
