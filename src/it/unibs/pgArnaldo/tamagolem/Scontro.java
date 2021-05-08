package it.unibs.pgArnaldo.tamagolem;

public class Scontro {

    //attributi
    private Squadra squadra1;
    private Squadra squadra2;
    private Squadra vincitore;
    private boolean conclusa;

    //costruttore
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
}
