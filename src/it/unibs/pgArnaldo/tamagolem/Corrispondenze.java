package it.unibs.pgArnaldo.tamagolem;


import java.util.HashMap;
import java.util.Map;

public class Corrispondenze
{

    //ATTRIBUTO
    Map<Integer, String> lista_corrispondenze;
    Map<String, Integer> lista_corrispondenze_reverse;

    //COSTRUTTORE

    /**
     * Creo 2 map:
     *     1) con la corrispondenza intero -> stringa, che è lista_corrispondenze
     *     2) con la corrispondenza stringa -> intero, che è lista_corrispondenze_reverse
     */
    public Corrispondenze (){
        lista_corrispondenze = new HashMap<Integer, String>();
        lista_corrispondenze.put(0, "erba");
        lista_corrispondenze.put(1, "acqua");
        lista_corrispondenze.put(2, "fuoco");
        lista_corrispondenze.put(3, "aria");
        lista_corrispondenze.put(4, "terra");
        lista_corrispondenze.put(5, "ghiaccio");
        lista_corrispondenze.put(6, "elettro");
        lista_corrispondenze.put(7, "buio");
        lista_corrispondenze.put(8, "luce");

        lista_corrispondenze_reverse = new HashMap<String, Integer>();
        for(int i=0; i < 9; i++){
            this.lista_corrispondenze_reverse.put(lista_corrispondenze.get(i), i);
        }
    }
    //GETTER
    public Map<Integer, String> getLista_corrispondenze() {
        return lista_corrispondenze;
    }
    public Map<String, Integer> getLista_corrispondenze_reverse() {
        return lista_corrispondenze_reverse;
    }

    /**
     * <h3>metodo che ritorna il nome della pietra, data la key inserita</h3>
     * @param i rappresenta la key
     * @return String nome della pietra
     */
    public String getNome(int i) { return lista_corrispondenze.get(i); }

    /**
     * <h3>metodo che ritorna l'integer della pietra, data la stringa inserita (lavora sulla hashmap reverse)</h3>
     * @param str rappresenta la stringa(la key della hashmap reverse)
     * @return Integer numero della pietra
     */
    public Integer getKey(String str) {
        return lista_corrispondenze_reverse.get(str);
    }

}
