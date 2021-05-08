package it.unibs.pgArnaldo.tamagolem;

import java.util.ArrayList;

public class Squadra
{
    //attributi
    private ArrayList<Tamagolem> lista_tamagolem; //lista per i tamagolem
    private ArrayList<String> lista_pietre;                   //lista per il numero di pietre
    private int puntaggio;

    //costruttore
    public Squadra(ArrayList<Tamagolem> lista_tamagolem, ArrayList<String> lista_pietre, int puntaggio) {
        this.lista_tamagolem = lista_tamagolem;
        this.lista_pietre = lista_pietre;
        this.puntaggio = puntaggio;
    }

    //set
    public void setLista_tamagolem(ArrayList<Tamagolem> lista_tamagolem) {
        this.lista_tamagolem = lista_tamagolem;
    }

    public void setLista_pietre(ArrayList<String> lista_pietre) {
        this.lista_pietre = lista_pietre;
    }

    public void setPuntaggio(int puntaggio) {
        this.puntaggio = puntaggio;
    }

    //get
    public ArrayList<Tamagolem> getLista_tamagolem() {
        return lista_tamagolem;
    }

    public ArrayList<String> getLista_pietre() {
        return lista_pietre;
    }

    public int getPuntaggio() {
        return puntaggio;
    }

    //togli pietre
    public void togliPietre(String tipo_pietra){
        this.lista_pietre.remove(tipo_pietra);
    }

    //togli tamagolem
    public void togliTamagolem(Tamagolem tamagolem_da_eliminare){
        this.lista_tamagolem.remove(tamagolem_da_eliminare);
    }
}
