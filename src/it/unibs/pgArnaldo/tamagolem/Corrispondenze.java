package it.unibs.pgArnaldo.tamagolem;


import java.util.HashMap;
import java.util.Map;

public class Corrispondenze
{
    //ATTRIBUTO
    Map<Integer, String> lista_corrispondenze;
    Map<String, Integer> lista_corrispondenze_reverse;

    //COSTRUTTORE
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

    }
    //GETTER
    public Map<Integer, String> getLista_corrispondenze() {
        return lista_corrispondenze;
    }

    /**
     * metodo che ritorna il nome della pietra, data la key inserita
     * @param i rappresenta la key
     * @return String nome della pietra
     */
    public String getNome(int i) { return lista_corrispondenze.get(i); }

}
