package view;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoView {
    public void exibirMensagem(String msg){
        System.out.println(msg);
    }

    public void listarProdutos(ArrayList<Produto> produtos){
        System.out.println("Lista de produtos: ");
        for(Produto p : produtos){
            System.out.println(p);
        }
    }

    public void exibirBusca(Produto p){
        if (p == null){
            System.out.println("Produto não encontrado.");
            return;
        }
        System.out.println(p);
    }
}
