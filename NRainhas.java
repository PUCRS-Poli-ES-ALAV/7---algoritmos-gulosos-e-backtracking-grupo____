/*

## O problema

O problema das N-rainhas consiste em encontrar uma combinação possível de N rainhas num tabuleiro de dimensão N por N tal que nenhuma das rainhas ataque qualquer outra. Duas rainhas atacam-se uma à outra quando estão na mesma linha, na mesma coluna ou na mesma diagonal do tabuleiro. Na figura que se segue pode ver-se as posições atacadas por uma rainha colocada num tabuleiro de dimensão 7 por 7 e ao lado uma possível solução para esse mesmo tabuleiro.
https://github.com/PUCRS-Poli-ES-ALAV/7-algoritmos-gulosos-e-backtracking/blob/main/nrainhas1.bmp

1. Desenvolver uma aplicação que resolva o problema das n-rainhas, encontrando uma solução válida para o problema. Como entrada, o programa recebe um valor para n >= 2, e retorna a disposição das rainhas no tabuleiro. Utilize uma estratégia de backtracking.

1. Ajuste o algoritmo anterior, para que retorne todas as soluções possíveis.

*/

public class NRainhas {

    static int solucoes = 0;

    public static void resolver(int tabuleiro[], int linha, int n) {

        if (linha == n) {

            solucoes++;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tabuleiro[i] == j)
                        System.out.print("Q");
                    else
                        System.out.print(".");
                }
                System.out.println();
            }

            System.out.println("");
            System.out.println("=".repeat(n));
            System.out.println("");
            return;
        }

        for (int coluna = 0; coluna < n; coluna++) {

            boolean podeColocar = true;

            for (int i = 0; i < linha; i++) {
                if (tabuleiro[i] == coluna)
                    podeColocar = false;
                if (Math.abs(tabuleiro[i] - coluna) == Math.abs(i - linha))
                    podeColocar = false;
            }

            if (podeColocar) {
                tabuleiro[linha] = coluna;
                resolver(tabuleiro, linha + 1, n);
            }
        }
    }

    public static void resultados(int tabuleiro[], int n) {

        resolver(tabuleiro, 0, n);

        System.out.println("N = " + n);
        System.out.println("Total de solucoes: " + solucoes);
        System.out.println("");
        System.out.println("=".repeat(n+1));
        System.out.println("");

    }

    public static void main(String[] args) {

        int testes = 7;

        for (int i = 2; i <= testes; i++) {

            solucoes = 0;

            int tabuleiro[] = new int[i];
            
            resultados(tabuleiro, i);
        }
    }
}