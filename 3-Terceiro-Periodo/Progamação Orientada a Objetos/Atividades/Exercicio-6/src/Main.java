import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int numeroAleatorio = random.nextInt(100) + 1;
        int tentativa = 0;
        int chute = 0;

        System.out.println("--- Jogo de Adivinhação (1 a 100) ---");

        while (chute != numeroAleatorio) {
            System.out.print("Digite seu palpite: ");
            chute = Integer.parseInt(sc.nextLine());
            tentativa++;

            if (chute < numeroAleatorio) {
                System.out.println("O número é MAIOR.");
            } else if (chute > numeroAleatorio) {
                System.out.println("O número é MENOR.");
            }
        }

        System.out.println("\nParabéns! Você acertou em " + tentativa + " tentativas.");
        sc.close();
    }
}