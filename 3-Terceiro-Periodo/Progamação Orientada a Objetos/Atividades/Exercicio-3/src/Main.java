import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Contagem de 1 a 100 usando for e while
        System.out.println("--- Contagem 1 a 100 (for) ---");
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
        }

        System.out.println("\n\n--- Contagem 1 a 100 (while) ---");
        int j = 1;
        while (j <= 100) {
            System.out.print(j + " ");
            j++;
        }

        // 2. Tabuada de 1 a 10
        System.out.println("\n\n--- Tabuada ---");
        int numTabuada = 7;
        for (int i = 1; i <= 10; i++) {
            System.out.println(numTabuada + " x " + i + " = " + (numTabuada * i));
        }

        // 3. Soma de 1 a N
        System.out.println("\n--- Soma de 1 a N ---");
        int n = 50;
        int soma = 0;
        for (int i = 1; i <= n; i++) {
            soma += i;
        }
        System.out.println("A soma de 1 até " + n + " é: " + soma);

        // 4. Fatorial usando for
        System.out.println("\n--- Fatorial ---");
        int numFatorial = 5;
        long fatorial = 1;
        for (int i = 1; i <= numFatorial; i++) {
            fatorial *= i;
        }
        System.out.println("O fatorial de " + numFatorial + "! é: " + fatorial);

        // 5. Peça uma nota entre 0 e 10. Repita enquanto o valor for inválido.
        System.out.println("\n--- Validação de Nota ---");
        double nota;
        do {
            System.out.print("Digite uma nota entre 0 e 10: ");
            nota = Double.parseDouble(sc.nextLine());
            System.out.println(nota);

            if (nota < 0 || nota > 10) {
                System.out.println("Valor inválido! Tente novamente.");
            }
        } while (nota < 0 || nota > 10);

        System.out.println("Nota validada: " + nota);

        sc.close();
    }
}