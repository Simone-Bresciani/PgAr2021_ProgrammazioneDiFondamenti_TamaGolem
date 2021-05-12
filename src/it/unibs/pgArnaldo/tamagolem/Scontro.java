package it.unibs.pgArnaldo.tamagolem;

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

    public int lancia(Tamagolem tamgolem1, Tamagolem tamagolem2, Corrispondenze lista_corrispondenza){
        int vita1= tamgolem1.getVita();
        int vita2= tamagolem2.getVita();
        for(int i=0; vita1 >0 || vita2>0 ; i++){
            //controllo che i sia arrivata alla finr dell'array, per poi far ripartire l'array dalla prima pietra
            if(i == tamgolem1.getPietre_utilizzate().size()){
                i=0;
            }
            for(int k=0; k<9; k++){
                if(tamgolem1.getPietre_utilizzate().get(i).equals(lista_corrispondenza.getNome(k))){

                }
            }

        }
        if(vita1 <= 0 && vita2 <= 0){
            return 3;
        }else if(vita1 <= 0){
            return  1;
        }else
            return 2;
    }
}
