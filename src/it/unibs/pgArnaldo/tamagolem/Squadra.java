package it.unibs.pgArnaldo.tamagolem;

import java.util.ArrayList;

public class Squadra
{
    //attributi
    private ArrayList<Tamagolem> lista_tamagolem; //lista per i tamagolem
    private ArrayList<String> lista_pietre; //lista per il numero di pietre

    //costruttore
    public Squadra(ArrayList<Tamagolem> lista_tamagolem, ArrayList<String> lista_pietre) {
        this.lista_tamagolem = lista_tamagolem;
        this.lista_pietre = lista_pietre;
    }

    //set
    public void setLista_tamagolem(ArrayList<Tamagolem> lista_tamagolem) {
        this.lista_tamagolem = lista_tamagolem;
    }

    public void setLista_pietre(ArrayList<String> lista_pietre) {
        this.lista_pietre = lista_pietre;
    }


    //get
    public ArrayList<Tamagolem> getLista_tamagolem() {
        return lista_tamagolem;
    }

    public String getLista_pietre(int numero_elementi) {
        String nomi_pietre = new String();
        nomi_pietre += "ZAINO: [ ";

        /*
        String elemento_corrente = lista_pietre.get(0);
        int i=0;
        int k=0;
        int scambiato  = 0;
        do{
            if(scambiato == 1){
                nomi_pietre += lista_pietre.get(i-1) + " x" + k;
                k = 0;
                scambiato = 0;
            }
            if(lista_pietre.get(i).equals(elemento_corrente)){
                k++;

            }else {
                elemento_corrente = lista_pietre.get(i);
                scambiato=1;
            }
            i++;
        }while(i<lista_pietre.size());
        nomi_pietre += " ]";
*/
       // /*
        for(int j=0; j< lista_pietre.size()-1; j++) {
            if(!lista_pietre.get(j).equals(lista_pietre.get(j+1))) {
                int k = 0;
                for (int i = 0; i < lista_pietre.size(); i++) {
                    if (lista_pietre.get(i).equals(lista_pietre.get(j))) k++;
                }
                nomi_pietre += lista_pietre.get(j) + "(x" + k + ") ";

            }
        }
        int f=0;
        for (int i = 0; i < lista_pietre.size(); i++) {
            if (lista_pietre.get(i).equals(lista_pietre.get(lista_pietre.size()-1))) f++;
        }
        nomi_pietre += lista_pietre.get(lista_pietre.size()-1) + "(x" + f + ") ";
        nomi_pietre += "]";
      //  */

        return nomi_pietre;
    }


    //togli pietre

    /**
     *
     * @param tipo_pietra
     * @return ritorna true se la pietra e' stata tolta correttamente
     */
    public boolean togliPietre(String tipo_pietra){
        if(this.lista_pietre.contains(tipo_pietra)){
            this.lista_pietre.remove(tipo_pietra);
            return true;
        }else return false;
    }

    //togli tamagolem
    public void togliTamagolem(Tamagolem tamagolem_da_eliminare){
        this.lista_tamagolem.remove(tamagolem_da_eliminare);
    }
}
