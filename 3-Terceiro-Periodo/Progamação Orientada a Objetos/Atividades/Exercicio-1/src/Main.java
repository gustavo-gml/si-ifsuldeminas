public class Main {
    public static void main(String[] args) {

        // 1. Declare duas variáveis inteiras e: some, subtraia, multiplique e divida, mostrando os resultados na tela.
        int num1 = 15;
        int num2 = 5;
        System.out.println("--- Operadores Básicos ---");
        System.out.println("Soma: " + (num1 + num2));
        System.out.println("Subtração: " + (num1 - num2));
        System.out.println("Multiplicação: " + (num1 * num2));
        System.out.println("Divisão: " + (num1 / num2));

        // 2. Crie uma variável inteira e mostre o resultado da divisão desse número por 3.
        int valor = 27;
        System.out.println("\n--- Divisão por 3 ---");
        System.out.println("O resultado de " + valor + " dividido por 3 é: " + (valor / 3));

        // 3. Declare três notas double e calcule a média.
        // Depois mostre: “Aprovado”, se média >= 7, “Recuperação”, se média entre 5 e 6.9, “Reprovado” se média < 5
        double nota1 = 8.5;
        double nota2 = 6.0;
        double nota3 = 5.5;
        double media = (nota1 + nota2 + nota3) / 3;

        System.out.println("\n--- Cálculo de Média ---");
        System.out.printf("Média final: %.2f\n", media);

        if (media >= 7) {
            System.out.println("Status: Aprovado");
        } else if (media >= 5) {
            System.out.println("Status: Recuperação");
        } else {
            System.out.println("Status: Reprovado");
        }

        // 4. Dada duas variáveis inteiras, mostre se são iguais, a primeira é maior ou a segunda é maior.
        int x = 10;
        int y = 20;
        System.out.println("\n--- Comparação de Valores ---");
        if (x == y) {
            System.out.println("Os números são iguais.");
        } else if (x > y) {
            System.out.println("A primeira variável (" + x + ") é maior que a segunda (" + y + ").");
        } else {
            System.out.println("A segunda variável (" + y + ") é maior que a primeira (" + x + ").");
        }
    }
}