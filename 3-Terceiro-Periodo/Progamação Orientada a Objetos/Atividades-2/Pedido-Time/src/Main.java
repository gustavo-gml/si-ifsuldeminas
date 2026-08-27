import entities.Cliente;
import entities.Pedido;
import entities.Time;
import entities.jogador;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //Cliente - Pedido - ItemPedido
        System.out.print("Digite o nome do cliente que realizou o pedido: ");
        String nomeCliente = sc.nextLine();

        System.out.print("Digite a quantidade de produtos do pedido: ");
        int quant = Integer.parseInt(sc.nextLine());

        Pedido p = new Pedido(new Cliente(nomeCliente), quant, sc);
        System.out.println(p);
        System.out.println("\n\n");



        //Time - Jogador
        System.out.print("Digite o nome do time: ");
        String nomeTime = sc.nextLine();

        Time time = new Time(nomeTime);

        System.out.print("Quantidade de jogadores: ");
        int quantJogadores = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < quantJogadores; i++){
            System.out.print("Digite o nome o jogador #" + (i+1) + ": ");
            String nomeJogador = sc.nextLine();

            time.addJogador(new jogador(nomeJogador));
        }

        System.out.println(time);
    }
}