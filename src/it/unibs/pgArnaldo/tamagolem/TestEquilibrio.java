package it.unibs.pgArnaldo.tamagolem;

import java.util.Random;

public class TestEquilibrio {

    static final int N = 5;
    static final int VITA = 10;

    public static void main(String[] args) {

        int[][] mat = new int[N][N];
        for(int i=0; i<N; i++){ //righe
            int w = VITA;
            int caselle_positive=0;
            for(int j=i+1; j!=N-2; j++){ // si ferma a n-2 perchè l'ultimo numero non può essere random
                Random rand = new Random();
                int pos = rand.nextInt(N-j+1)+i;
                if(mat[i][pos] == 0){
                    if(caselle_positive <= (N-1)/2){
                        mat[i][pos] = rand.nextInt(w)+1;
                        w -= mat[i][pos];
                        caselle_positive++;
                    }else{
                        mat[i][pos] = -rand.nextInt(VITA-w);
                        w -= -mat[i][pos];
                    }
                }
            }
        }

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                System.out.print("[" + mat[i][j] + "]");
            }
            System.out.print("\n");
        }
    }
}
