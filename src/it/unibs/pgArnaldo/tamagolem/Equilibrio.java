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
     * é un metodo che crea una <b>matrice simmetrica rispetto alla diagonale principale</b> che ha N(=numero di elementi in gioco) righe e N colonne, gli incroci di queste identificano
     * la potenza(+) della mossa con cui attaccano oppure la il danno(-) che subiscono. Lo 0 sta ad indicare una interazione tra elementi uguali per cui non ci sará
     * un vincitore tra i due. La matrice é stata costruita in modo tale che, immettendo un numero N dispari (che verrá settato pari a 3,5,7 o 9 a seconda della
     * difficoltá della partita scelta), preso un elemento qualiasi questo vincerá su metá degli elementi avversari e perderá contro l'altra metá in modo tale da
     * rendere veramente EQUILIBRATO il sistema di gioco laddove é possibile. La somma dei danni in output e dei danni ricevuti per ogni elemento é sempre pari a 0.
     * Il valore di interazione tra due elementi é sempre >=1.
     * Una volta calcolata la matrice dei danni che avrá validitá fino al termine della partita,
     * si stabilisce la VITA massima dei tamagolem (uguale per tutti e valida per tutto il corso della partita) in modo tale che sup(W)=VITA , dove W indica línsieme di
     * tutti i valori di potenza dell'interazione presenti nell'Equilibrio.
     * @param n rappresenta il numero della tipologia degli elementi (in base alla difficoltà scelta)
     * @return oggetto equilibrio contente la matrice, la vita ed il numero degli elementi
     */
    public Equilibrio(int n){

        this.mat = new int[n][n];
        Random rand = new Random();
        boolean invalido = false;
        this.n = n;

        do {
            invalido = false;

            //inizializzazione matrice pulita
            for(int a = 0; a< n; a++){
                for(int b = 0; b< n; b++){
                    mat[a][b] = 0;
                }
            }

            for (int i = n - 1; i > 1; i--) {  //inizialmente i=4, sto scorrendo dall'ultima colonna fino alla seconda
                int numeri_positivi_sulla_colonna = 0;
                int numeri_negativi_sulla_colonna = 0;
                int somma_numeri_colonna = 0;

                int somma_numeri_riga_precedente = 0;
                for (int s = 0; s < n; s++) {
                    somma_numeri_riga_precedente += mat[i][s];
                }


                if (rand.nextInt(2) == 1) {
                    mat[i - 1][i] = randomizzaPositivo();  //a caso estrarró un positivo o un negativo e lo inserisco nella posizione
                    numeri_positivi_sulla_colonna++;
                } else {
                    mat[i - 1][i] = randomizzaNegativo();
                    numeri_negativi_sulla_colonna++;
                }
                somma_numeri_colonna += mat[i - 1][i];


                for (int j = i - 1; j > 1; j--) {  //scorro le caselle della colonna tranne l'ultima che sará la somma restante
                    if (numeri_positivi_sulla_colonna < (n - 1) / 2 && numeri_negativi_sulla_colonna < (n - 1) / 2) {
                        if (rand.nextInt(2) == 1) {
                            mat[j - 1][i] = randomizzaPositivo();  //a caso estrarró un positivo o un negativo e lo inserisco nella posizione
                            numeri_positivi_sulla_colonna++;
                        } else {
                            mat[j - 1][i] = randomizzaNegativo();
                            numeri_negativi_sulla_colonna++;
                        }
                    } else if(numeri_negativi_sulla_colonna >= (n - 1) / 2){
                        mat[j - 1][i] = randomizzaPositivo();
                        numeri_positivi_sulla_colonna++;
                    }else {
                        mat[j - 1][i] = randomizzaNegativo();
                        numeri_negativi_sulla_colonna++;
                    }


                    somma_numeri_colonna += mat[j - 1][i];
                }

                mat[0][i] = somma_numeri_riga_precedente - somma_numeri_colonna;

            }
            //completo l'ultima casellina in alto a sinistra
            for (int s = 0; s < n; s++) {
                mat[0][1] += mat[1][s];
            }

            //copio la metá matrice e la convalido
            for (int a = 0; a< n -1; a++){
                for (int b = a+1; b< n; b++){
                    if(mat[a][b]==0) invalido = true;
                    mat[b][a] = -mat[a][b];
                }
            }


        }while(invalido);


        vita = calcolaVita(mat, n);

    }

    private int calcolaVita(int [][] mat, int n) {
        int vita = 1;
        for(int a=0; a<n; a++){
            for(int b=0; b<n; b++){
                if(mat[a][b] > vita) vita = mat[a][b];
            }
        }
        return vita;
    }

    private static int randomizzaPositivo() {
        Random rand = new Random();
        return rand.nextInt(3)+1;   //un numero cauale tra 1,2,3 (uso dei numeri piccoli per comoditá)
    }

    private static int randomizzaNegativo() {
        Random rand = new Random();
        return -rand.nextInt(3)-1; //un numero cauale tra -1,-2,-3 (uso dei numeri piccoli per comoditá)
    }

    public int getVita() {
        return vita;
    }


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