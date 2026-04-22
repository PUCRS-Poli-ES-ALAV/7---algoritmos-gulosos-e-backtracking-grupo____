/*

## Problema do Troco

Suponha que tenhamos disponíveis moedas com certos valores (por exemplo, de 100, 25, 10, 5 e 1). O problema do
troco consiste criar um algoritmo que para conseguir obter um determinado valor com o menor número de moedas ´
possível.
Por exemplo, para “dar um troco” de R$2,89, a melhor solução, isto é, o menor número de 
moedas possível para obter o valor consiste em 10 moedas: 2 de valor 100, 3 de valor 25, 1 de valor 10
e 4 de valor 1.

1) **Objetivo:** contrua um algorítmo que recebe a lista das moedas disponíveis e um valor, e retorna uma lista com a menor
quantidade de moedas para este troco;
  * Defina uma assinatura adequada para este método;
  * Utiliza uma abordagem gulosa (se puder);
  * Contabilize e exiba o número de iterações para cada caso de teste;
  * O exercício pode ser feito em grupos de um, dois ou três elementos.

*/

public class TrocoMoedas {

    static int iteracoes = 0;

    public static int[] calcularTroco(int moedas[], int valor) {
        
        iteracoes = 0;

        int resultado[] = new int[moedas.length];

        for (int i = 0; i < moedas.length; i++) {
            
            iteracoes++;

            if (valor >= moedas[i]) {
                resultado[i] = valor / moedas[i];
                valor = valor % moedas[i];

                if (valor == 0)
                    break;
            }
        }

        return resultado;
    }

    public static void resultado(int valor, int moedas[], int resultado[]) {
        
        System.out.print("Valor: " + valor +
                         "\nMoedas usadas: [");
        
        for (int i = 0; i < moedas.length; i++) {
            System.out.print(moedas[i] + ": " + resultado[i]);

            if (i < moedas.length - 1)
                System.out.print(", ");
        }
        
        System.out.println("]\nIteracoes: " + iteracoes + "\n");
    }

    public static void main(String[] args) {

        int moedas[] = {100, 25, 10, 5, 1};
        int moedas2[] = {100, 20, 10, 5, 1};
        int moedas3[] = {100, 30, 25, 10, 5, 1};

        int valor1 = 289;
        int valor2 = 2604;
        int valor3 = 1309;
        int valor4 = 90125;
        int valor5 = 100;

        System.out.println("=".repeat(60));
        System.out.println("\nMoedas 1 (100, 25, 10, 5, 1)\n");
        resultado(valor1, moedas, calcularTroco(moedas, valor1));
        resultado(valor2, moedas, calcularTroco(moedas, valor2));
        resultado(valor3, moedas, calcularTroco(moedas, valor3));
        resultado(valor4, moedas, calcularTroco(moedas, valor4));
        resultado(valor5, moedas, calcularTroco(moedas, valor5));
        System.out.println("=".repeat(60));

        System.out.println("\nMoedas 2 (100, 20, 10, 5, 1)\n");
        resultado(valor1, moedas2, calcularTroco(moedas2, valor1));
        resultado(valor2, moedas2, calcularTroco(moedas2, valor2));
        resultado(valor3, moedas2, calcularTroco(moedas2, valor3));
        resultado(valor4, moedas2, calcularTroco(moedas2, valor4));
        resultado(valor5, moedas2, calcularTroco(moedas2, valor5));
        System.out.println("=".repeat(60));

        System.out.println("\nMoedas 3 (100, 30, 25, 10, 5, 1)\n");
        resultado(valor1, moedas3, calcularTroco(moedas3, valor1));
        resultado(valor2, moedas3, calcularTroco(moedas3, valor2));
        resultado(valor3, moedas3, calcularTroco(moedas3, valor3));
        resultado(valor4, moedas3, calcularTroco(moedas3, valor4));
        resultado(valor5, moedas3, calcularTroco(moedas3, valor5));
        System.out.println("=".repeat(60));
    }
}