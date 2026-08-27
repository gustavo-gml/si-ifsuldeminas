import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double saldo = 1000.0;
        int opcao = 0;

        System.out.println("--- Bem-vindo ao Caixa Eletrônico ---");

        while (opcao != 4) {
            System.out.println("\n1 - Ver Saldo | 2 - Sacar | 3 - Depositar | 4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:
                    System.out.printf("Saldo atual: R$ %.2f\n", saldo);
                    break;
                case 2:
                    System.out.print("Valor do saque: ");
                    double saque = Double.parseDouble(sc.nextLine());
                    if (saque > saldo) {
                        System.out.println("Erro: Saldo insuficiente.");
                    } else {
                        saldo -= saque;
                        System.out.println("Saque realizado com sucesso.");
                    }
                    break;
                case 3:
                    System.out.print("Valor do depósito: ");
                    double deposito = Double.parseDouble(sc.nextLine());
                    saldo += deposito;
                    System.out.println("Depósito realizado.");
                    break;
                case 4:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
        sc.close();
    }
}