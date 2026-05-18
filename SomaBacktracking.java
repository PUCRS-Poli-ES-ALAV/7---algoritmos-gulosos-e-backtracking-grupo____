/*

Problema da Soma dos Subconjuntos

O problema da soma dos subconjuntos é um problema da Ciência da Computação que consiste em verificar se, dado um conjunto de inteiros, existe um subconjunto não-vazio cuja soma é zero.

Por exemplo, no conjunto {−7, −3, −2, 5, 8}, a resposta é sim, pois o subconjunto {−3, −2, 5} resulta em uma soma de zero. 

    Faça um método, utilizando backtracking, que recebe um conjunto de inteiros e retorna um subconjunto cuja soma seja zero;
    Altere o método para que retorne todos subconjuntos cuja soma seja zero;
    Analise a complexidade de ambas as soluções.
    Teste para outros exemplos, tais como:
        {1, 2, 3, 4, 5, 10}
        {-5, 2, 3, -1, 1}
    Faça um código que gera conjuntos grandes de números positivos e negativos (entre 50 e 1000 elementos) e use estes conjuntos para testar a performance (e entender a complexidade).

    */


import java.util.Random;

public class SomaBacktracking {

    static long iteracoes = 0;
    static long solucoes = 0;

    public static void resolver(int conjunto[], int atual[], int indice, int tamanhoAtual, long somaAtual, long positivosRestantes[], long negativosRestantes[]) {

        iteracoes++;

        long menorSomaPossivel = somaAtual + negativosRestantes[indice];
        long maiorSomaPossivel = somaAtual + positivosRestantes[indice];

        if (menorSomaPossivel > 0 || maiorSomaPossivel < 0)
            return;

        if (indice == conjunto.length) {
            if (somaAtual == 0 && tamanhoAtual > 0) {
                solucoes++;
            }
            return;
        }

        atual[tamanhoAtual] = conjunto[indice];

        resolver(conjunto, atual, indice + 1, tamanhoAtual + 1, somaAtual + conjunto[indice], positivosRestantes, negativosRestantes);
        resolver(conjunto, atual, indice + 1, tamanhoAtual, somaAtual, positivosRestantes, negativosRestantes);
    }

    public static void resultados(int caso, int conjunto[]) {

        long positivosRestantes[] = new long[conjunto.length + 1];
        long negativosRestantes[] = new long[conjunto.length + 1];

        for (int i = conjunto.length - 1; i >= 0; i--) {

            positivosRestantes[i] = positivosRestantes[i + 1];
            negativosRestantes[i] = negativosRestantes[i + 1];

            if (conjunto[i] > 0) {
                positivosRestantes[i] += conjunto[i];
            } else {
                negativosRestantes[i] += conjunto[i];
            }
        }

        int atual[] = new int[conjunto.length];

        iteracoes = 0;
        solucoes = 0;

        resolver(conjunto, atual, 0, 0, 0, positivosRestantes, negativosRestantes);

        System.out.print("caso " + caso + " - array[");

        for (int i = 0; i < conjunto.length; i++) {
            System.out.print(conjunto[i]);

            if (i < conjunto.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");

        System.out.println("iteracoes " + iteracoes);
        System.out.println("solucoes " + solucoes);
        System.out.println();
    }

    public static void main(String[] args) {

        int teste1[] = {-7, -3, -2, 5, 8};
        int teste2[] = {1, 2, 3, 4, 5, 10};
        int teste3[] = {-5, 2, 3, -1, 1};
        int teste4[] = {-10, -4, -3, 1, 2, 5, 9};

        resultados(1, teste1);
        resultados(2, teste2);
        resultados(3, teste3);
        resultados(4, teste4);

        Random random = new Random(10);

        int teste5[] = new int[10];
        int teste6[] = new int[25];
        int teste7[] = new int[50];
        int teste8[] = new int[75];

        for (int i = 0; i < 75; i++) {
            if (i < 10)
                teste5[i] = random.nextInt(2001) - 1000;
            if (i < 25)
                teste6[i] = random.nextInt(2001) - 1000;
            if (i < 50)
                teste7[i] = random.nextInt(2001) - 1000;

            teste8[i] = random.nextInt(2001) - 1000;
        }

        resultados(5, teste5);
        resultados(6, teste6);
        resultados(7, teste7);
        resultados(8, teste8);
    }
}