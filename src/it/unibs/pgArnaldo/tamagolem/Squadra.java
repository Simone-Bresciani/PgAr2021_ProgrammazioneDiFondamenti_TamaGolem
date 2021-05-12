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

    /**
     * Metodo per comunicare la lista pietre in modo più leggibile per l'utente
     * Esempio di funzionamento:
     *     al posto di comunicare [acqua, acqua, terra, terra] comunica [acqua(x2), terra(x2)]
     * @param numero_elementi ovvero il numero delle tipologie di pietre
     * @return nomi_pietre è una stringa che comunica le pietre totali a disposizione
     */
    public String getLista_pietre(int numero_elementi) {
        //sarà la stringa che ritornerà con le pietre disponibili
        String nomi_pietre = new String();
        //in testa alla stringa metterò l'apertura dello zaino
        nomi_pietre += "ZAINO: [ ";
        //ciclo per la lunghezza dell'arraylist, senza l'ultima posizione
        //altrimenti darebbe errore a confrontare l'ultimo elemento dell'array con l'elemento dopo

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
        return nomi_pietre;
    }

    /**
     *
     * @param tipo_pietra ovvero il nome che la rappresenta
     * @return ritorna true se la pietra e' stata tolta correttamente
     */
    public boolean togliPietre(String tipo_pietra){
        if(this.lista_pietre.contains(tipo_pietra)){
            this.lista_pietre.remove(tipo_pietra);
            return true;
        }else return false;
    }

    /**
     *
     * @param tamagolem_da_eliminare ovvero l'oggetto tamagolem
     */
    public void togliTamagolem(Tamagolem tamagolem_da_eliminare){
        this.lista_tamagolem.remove(tamagolem_da_eliminare);
    }
}
