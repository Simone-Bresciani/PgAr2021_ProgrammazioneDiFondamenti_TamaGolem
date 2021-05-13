package it.unibs.pgArnaldo.tamagolem;

import java.util.Random;


public class Equilibrio
{
    //ATTRIBUTI
    private int [][] mat;
    private int vita;
    private int n;

    //METODO  EQUILIBRIO
    /**
     * Metodo <b>costruttore</b> che crea<br>l'equilibrio con <b>n</b> numero elementi, <b>la vita massima</b> e </n>una <b>matrice antisimmetrica rispetto la diagonale principale</b><br>
     * La matrice possiede: N(=numero di elementi in gioco) righe e N colonne;<br>gli incroci di queste identificano
     * la potenza(+) della mossa con cui attaccano oppure la il danno(-) che subiscono.<br>
     * Lo 0 sta ad indicare una interazione tra elementi uguali per cui non ci sará
     * un vincitore tra i due.<br>
     * La matrice é stata costruita in modo tale che la somma dei danni in output e dei danni ricevuti per ogni elemento é sempre pari a 0.<br>
     * Il valore di interazione tra due elementi é sempre maggiore di 0.<br>
     * Una volta calcolata la matrice dei danni che avrá validitá fino al termine della partita,
     * si stabilisce la VITA massima dei tamagolem (uguale per tutti e valida per tutto il corso della partita) in modo tale che sup(W)=VITA , dove W indica línsieme di
     * tutti i valori di potenza dell'interazione presenti nell'Equilibrio.
     * @param n rappresenta il numero della tipologia degli elementi (in base alla difficoltà scelta).
     */
    public Equilibrio(int n){

        this.mat = new int[n][n];
        this.n = n;

            //sto scorrendo dall'ultima colonna fino alla prima
            for (int i = n - 1; i > 0; i--) {
                int somma_numeri_colonna = 0;
                int somma_numeri_riga_precedente = 0;

                /* calcolo la somma dei valori presenti nella riga sotto la colonna che sto controllando scorrendo da destra a sinistra
                   fino ad arrivare allo zero presente sulla diagonale principale
                   poiché questa somma dovrá essere uguale alla somma dei valori che metteró sulla colonna che sto controllando */
                for (int s = n - 1; s >= i; s--) {
                    somma_numeri_riga_precedente += mat[i][s];
                }

                //ora scorro le caselle della colonna partendo da quella piú in basso fino alla penultima disponibile in alto perché l'ultima dovrá per forza essere la somma restante
                //in pratica sto scorrendo una matrice triangolare superiore, la cui diagonale é peró composta da zeri, dal vertice in basso a destra salendo in su e poi spostandomi di una colonna a sinistra
                for (int j = i - 1 ; j > 0; j--) {

                    //randomizzo il numero in questa posizione
                    mat[j][i] = randomizzaCasuale();

                    //piccola eccezione per l'ultimo numero che dovró generare casualmente perché potrebbe rendermi 0 l'ultima casella che completeró per differenza
                    //oltre dunque un'ulteriore condizione di controllo nel while
                    if(i==2 && j==1){
                        int somma_riga_corrente= 0;
                        for(int s = 3; s < n; s++){
                            somma_riga_corrente += mat[j][s];
                        }
                        while(mat[j][i] == -somma_riga_corrente  || mat[j][i] == -somma_numeri_colonna || mat[j][i] + somma_numeri_colonna == somma_numeri_riga_precedente){
                            mat[j][i] = randomizzaCasuale();
                        }

                        //sono alla seconda riga dall'alto e non voglio che il valore che inserisco mi annulli la somma_numeri_colonna perché se andasse cosí allora
                        //quando si va a completare l'ultima casella della colonna si assegna uno 0 e questo non é ammesso.
                    }else if (j == 1) {
                            //continuo a rigenerarlo fino a che non trovo un numero che non renda 0 la somma_numeri_colonna sia a causa della riga sia della colonna
                            while (mat[j][i] == -somma_numeri_colonna || mat[j][i] + somma_numeri_colonna == somma_numeri_riga_precedente) {
                                mat[j][i] = randomizzaCasuale();
                            }
                    }

                    //specchio antisimmetricamente il valore appena assegnato
                    mat[i][j] = -mat[j][i];
                    //sommo i vari numeri che man man aggiungo alla porzione di colonna
                    somma_numeri_colonna += mat[j][i];
                }
                //l'ultimo valore della colonna deve essere la differenza tra le due somme
                mat[0][i] = somma_numeri_riga_precedente - somma_numeri_colonna;
                //lo specchio antisimmetricamente
                mat[i][0] = -mat[0][i];

            }
        //invoco il metodo calcolaVita e assegno il valore all'attributo
        vita = calcolaVita(mat, n);
    }

    //GETTER
    public int getVita() {
        return vita;
    }

    /**
     * Metodo che data la matrice assegna il suo valore massimo alla vita
     * @param mat matrice dell'equilibrio
     * @param n numero elementi, lunghezza delle righe e colonne della matrice
     * @return
     */
    private int calcolaVita(int [][] mat, int n) {
        int vita = 1;
        for(int a=0; a<n; a++){
            for(int b=0; b<n; b++){
                if(mat[a][b] > vita) vita = mat[a][b];
            }
        }
        return vita;
    }

    /**
     * Metodo che randomizza un intero
     * @return intero casuale tra -3 e -1 inclusi oppure tra 1 e 3 inclusi
     */
    private static int randomizzaCasuale() {
        Random rand = new Random();
        //randomizzo tra 1 e 2 e invoco un positivo oppure un negativo a secondo del risultato
        if(rand.nextInt(2)== 1){
            return rand.nextInt(3)+1;
        }else return -rand.nextInt(3)-1;
    }



    /**
     * <h3>Metodo per la comiunicazione della matrice</h3>
     * @return mat corrisponde alla matrice
     */
    public int[][] getMat() {
        return mat;
    }

    /**
     * <h3>Metodo per la stampa della matrice dell'equilibrio a fine partita</h3>
     */
    public void stampaMatriceEquilibrio() {
        for (int a=0; a<n; a++){
            for (int b=0; b<n; b++){
                if (mat[a][b] < 0) System.out.printf("[" + mat[a][b] + "]" + " ");
                else System.out.printf("[ " + mat[a][b] + "]" + " ");
            }
            System.out.print("\n");
        }System.out.print("\n");
    }
}