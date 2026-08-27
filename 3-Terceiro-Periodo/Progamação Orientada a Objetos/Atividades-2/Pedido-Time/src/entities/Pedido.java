package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Pedido {

    private ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(Cliente cliente, int quantidade, Scanner sc) {
        this.itens = new ArrayList<>();
        this.cliente = cliente;
        adicionarPedido(quantidade, sc);
    }

    private void adicionarPedido(int quantidade, Scanner sc ){

        for(int i = 0; i < quantidade; i++){
            System.out.print("Digite o nome do produto #"+(i+1) + ": ");
            String nome = sc.nextLine();

            System.out.print("Digite o preco do produto #"+(i+1) + ": ");
            Double preco = Double.parseDouble(sc.nextLine());
            System.out.println();

            itens.add(new ItemPedido(nome, preco));
        }
        System.out.println("Pedido realizado com sucesso !");

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do cliente: " + this.cliente);
        for (ItemPedido i : itens){
            sb.append(i.toString());
        }
            return sb.toString();
    }
}
