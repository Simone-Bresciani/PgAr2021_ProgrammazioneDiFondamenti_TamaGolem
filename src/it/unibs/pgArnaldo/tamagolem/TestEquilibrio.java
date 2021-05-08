package it.unibs.pgArnaldo.tamagolem;

import java.util.Random;

public class TestEquilibrio {

    static final int N = 5;
    static final int VITA = 10;

    public static void main(String[] args) {


        int[][] mat = new int[N][N];
        for(int i=0; i<N-2; i++){ //righe tranne l'ultima
            int caselle_positive=0;
            //int num = N;
            int caselle_negative =0;
            int max_caselle_per_segno = (N-1)/2;  //se N é dispari(unica situazione da tenere in considerazione secondo me perché piú equilibrata)
            int somma_controllo_positivi=0;
            int somma_controllo_negativi=0;
            for(int n=0; n< N; n++){
                if(mat[i][n] > 0) {
                    somma_controllo_positivi += mat[i][n];
                    caselle_positive += 1;
                }
                if(mat[i][n] < 0) {
                    somma_controllo_negativi += mat[i][n];
                    caselle_negative += 1;
                }
            }
            int w = VITA - somma_controllo_positivi;
            int revers = -somma_controllo_negativi + somma_controllo_positivi;

            for(int j=i+1; j!=N; j++){ // itero per un numero di volte pari alle posizioni disponibili
                Random rand = new Random();
                int pos = rand.nextInt(N-i-1) +i+1;  //numero randomico che va da i+1 a N-1
                if(mat[i][pos] == 0){
                    if (caselle_positive < max_caselle_per_segno){
                        mat[i][pos] = mat[pos][i] = rand.nextInt(w -(max_caselle_per_segno - caselle_positive)) + 1;//(( w - (num-2)/2 ) - ) +1;
                        w -= mat[i][pos];
                        revers += mat[i][pos];
                        caselle_positive++;
                        //num -= 2;

                    }else{
                        if (max_caselle_per_segno - caselle_negative == 1){       //regola l'ultima cella della riga
                            mat[i][pos] = mat[pos][i]= -revers;
                        }else {
                                mat[i][pos] = mat[pos][i]=  -rand.nextInt(revers - (max_caselle_per_segno-caselle_negative)) - 1; //revers é la quantitá positiva somma delle casellle giá assegnate in quella riga;
                                revers += mat[i][pos];
                                caselle_negative ++;
                             }
                    }
                }else j -= 1; //se é una casella in cui ho giá scritto allora dovró randomizzare di nuovo la posizione e non contare l'iterazione del ciclo che é precisa per i cambiamenti da fare
            }
        }
        //completamento ultima cella
        for(int i=0; i< N-1; i++ ) {
            mat[N - 2][N-1] = mat[N-1][N - 2] -= mat[N - 2][i];
        }
        //STAMPA
        for (int a=0; a<N; a++){
            for (int b=0; b<N; b++){
                if (mat[a][b] < 0) System.out.printf("[" + mat[a][b] + "]" + " ");
                else System.out.printf("[ " + mat[a][b] + "]" + " ");
            }
            System.out.print("\n");
        }System.out.print("\n");

    }
}
