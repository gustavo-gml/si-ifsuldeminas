import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. Mostre apenas os números pares de 1 a 100 usando continue
        System.out.println("--- Números Pares (1-100) com continue ---");
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                continue;
            }
            System.out.print(i + " ");
        }

        // 2. Peça números ao usuário e se ele digitar número negativo, use break para encerrar
        System.out.println("\n\n--- Leitura com interrupção (break) ---");
        while (true) {
            System.out.print("Digite um número (negativo para parar): ");
            double num = Double.parseDouble(sc.nextLine());
            if (num < 0) {
                break;
            }
        }

        // 3. Peça 10 números e informe quantos são positivos
        System.out.println("\n--- Contagem de Positivos ---");
        int positivos = 0;
        for (int i = 1; i <= 10; i++) {
            System.out.print("Digite o " + i + "º número: ");
            double num = Double.parseDouble(sc.nextLine());
            if (num > 0) {
                positivos++;
            }
        }
        System.out.println("Total de números positivos: " + positivos);

        // 4. Peça 5 números e informe o maior e o menor
        System.out.println("\n--- Maior e Menor de 5 números ---");
        double maior = 0, menor = 0;
        for (int i = 1; i <= 5; i++) {
            System.out.print("Digite o " + i + "º valor: ");
            double atual = Double.parseDouble(sc.nextLine());

            if (i == 1) {
                maior = atual;
                menor = atual;
            } else {
                if (atual > maior) maior = atual;
                if (atual < menor) menor = atual;
            }
        }
        System.out.println("Maior: " + maior + " | Menor: " + menor);

        // 5. Verifique se um número é primo
        System.out.println("\n--- Verificador de Número Primo ---");
        System.out.print("Digite um número inteiro: ");
        int primoCandidato = Integer.parseInt(sc.nextLine());
        boolean ehPrimo = true;

        if (primoCandidato <= 1) {
            ehPrimo = false;
        } else {
            for (int i = 2; i <= Math.sqrt(primoCandidato); i++) {
                if (primoCandidato % i == 0) {
                    ehPrimo = false;
                    break;
                }
            }
        }

        if (ehPrimo) {
            System.out.println(primoCandidato + " é primo.");
        } else {
            System.out.println(primoCandidato + " não é primo.");
        }

        sc.close();
    }
}