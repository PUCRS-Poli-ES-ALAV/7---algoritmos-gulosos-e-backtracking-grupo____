/*

## Escalonamento de Intervalos

### O Problema

Um *intervalo* é um conjunto de números naturais consecutivos.  Um intervalo como  *{s,s+1,…,f−1,f}*  será denotado por  *(s,f)*. O primeiro número do par é o início do intervalo e o segundo é o término.  (As letras *s* e *f* lembram *start* e *finish* respectivamente.)

Se temos vários intervalos, numerados de 1 a n, o início de um intervalo *i* será denotado por *s<sub>i</sub>* e o término por *f<sub>i</sub>*.  Suporemos sempre que *s<sub>i</sub> ≤ f<sub>i</sub>*.

Um intervalo i é anterior a um intervalo j se  *f<sub>i</sub> < s<sub>j</sub>*.  Analogamente, i é posterior a j se  *s<sub>i</sub> > f<sub>j</sub>*.   Dois intervalos i e j são disjuntos se e somente se i é posterior a j ou anterior a j.   Uma coleção de intervalos é disjunta se os intervalos da coleção são disjuntos dois a dois.

### Problema do escalonamento de intervalos

Dada uma coleção S de intervalos, encontrar uma subcoleção disjunta máxima de S.

Uma subcoleção disjunta X de S é *máxima* se não existe outra maior.  Em outras palavras, se não existe subcoleção disjunta *X′* de *S* tal que *|X′| > |X|*.

Usaremos a abreviatura *SDM* para a expressão subcoleção disjunta máxima.  Nosso problema consiste, portanto, em encontrar uma *SDM* de uma coleção de intervalos dada.  Se os intervalos são numerados de 1 a n, uma *SDM* pode ser representada por um subconjunto de * *{1,2,…,n}*.

**Exemplo** 
A figura abaixo especifica uma coleção de intervalos e uma sdm da coleção.  A SDM é indicada pelos 1 do seu vetor característico X:

```javascript
s 4 6 13 4 2 6 7  9  1 3  9
f 8 7 14 5 4 9 10 11 6 13 12
X 0 1 1  0 1 0 0  1  0 0  0
```

É fácil verificar que a coleção de 4 intervalos definida por x é disjunta. Mas não é óbvio que ela seja máxima. Você tem certeza de que não existem 5 intervalos disjuntos dois a dois?

### Algoritmo guloso

Nosso problema pode ser resolvido por um algoritmo guloso. Para descrever o algoritmo, precisamos do seguinte conceito. Digamos que o primeiro intervalo de um coleção de intervalos é o que tem o menor término.

Eis o esboço do algoritmo guloso. Ele recebe uma coleção S de intervalos e devolve uma sdm de S:

```javascript
X ← { }
enquanto S ≠ { } faça
   i ← primeiro intervalo de S
   X ← X ∪ {i}
   S ← coleção dos intervalos posteriores a i
devolva X
```

Antes de transformar esse esboço em pseudocódigo de nível mais baixo, convém exigir que os intervalos estejam em ordem crescente de término (Com isso, o primeiro intervalo é o de índice 1).

O algoritmo supõe f<sub>1</sub> ≤ … ≤ f<sub>n</sub> e n ≥ 0 e devolve uma sdm da coleção de intervalos definida por *(s,f,n)*:

```javascript
SDM-Guloso (s, f, n)
1   X ← { }
2   i ← 1
3   enquanto i ≤ n faça
4        X ← X ∪ {i}
5        k ← i + 1
6        enqunto k ≤ n e sk < fi faça
7              k ← k + 1
8        i ← k
9   devolva X
```

O código pode ser reescrito de maneira mais elegante:

```javascript
SDM-Guloso (s, f, n)
1   f0 ← −∞
2   X ← { }
3   i ← 0
4   para k ← 1 até n faça
5         se sk > fi
6              então X ← X ∪ {k}
7                       i ← k
8   devolva X
```

2) **Assim:**

* Implemente e teste uma solução para o problema descrito, usando aboradagem gulosa, como descrita;
* Contabilize e exiba o número de iterações para cada caso de teste;

*/

public class EscalonamentoIntervalos {

    static long iteracoes = 0;

    public static int[] calcularEscalonamento(int s[], int f[]) {
        
        iteracoes = 0;

        int ultimo = -1;

        int x[] = new int[s.length];

        for (int k = 0; k < s.length; k++) {
            iteracoes++;
            
            // Em "s[k] >= f[ultimo]", decidi fazer >= em vez de apenas > para maximizar o numero de intervalos,
            // sem o =, é não incluso, fazendo que, por exemplo, 5-8 e 8-11 não seja um intervalo

            if (ultimo == -1 || s[k] >= f[ultimo]) {
                x[k] = 1;
                ultimo = k;
            }
        }

        return x;
    }

    public static void resultado(String caso, int s[], int f[], int x[]) {
        
        int quantidade = 0;

        for (int i = 0; i < x.length; i++) {
            if (x[i] == 1)
                quantidade++;
        }

        System.out.println(caso);
        System.out.println("Iteracoes: " + iteracoes + "\n");

        System.out.print("s: [");
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]);
            if (i < s.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");

        System.out.print("f: [");
        for (int i = 0; i < f.length; i++) {
            System.out.print(f[i]);
            if (i < f.length - 1)
                System.out.print(", ");
        }
        System.out.println("]");

        System.out.print("X: [");
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i]);
            if (i < x.length - 1)
                System.out.print(", ");
        }
        System.out.println("] \n");

        System.out.println(quantidade + " intervalos de " + x.length + " selecionados \n");
    }

    public static void main(String[] args) {
        int s1[] = {0, 2, 1, 5, 4, 8, 7, 11, 10, 14};
        int f1[] = {3, 5, 6, 8, 9, 11, 12, 15, 16, 20};

        int s2[] = {0, 1, 3, 2, 6, 5, 9, 8, 12, 11, 15, 14};
        int f2[] = {4, 6, 8, 9, 11, 12, 15, 16, 19, 20, 23, 24};

        int s3[] = {2, 0, 1, 5, 4, 8, 7, 11, 10, 14, 13};
        int f3[] = {4, 6, 6, 7, 9, 10, 12, 13, 16, 18, 20};

        System.out.println("=".repeat(25));

        resultado("Teste 1", s1, f1, calcularEscalonamento(s1, f1));
        System.out.println("=".repeat(25) + "\n");

        resultado("Teste 2", s2, f2, calcularEscalonamento(s2, f2));
        System.out.println("=".repeat(25) + "\n");

        resultado("Teste 3", s3, f3, calcularEscalonamento(s3, f3));
        System.out.println("=".repeat(25) + "\n");
    }
}