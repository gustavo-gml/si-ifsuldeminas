package controller;

import model.Produto;
import service.ProdutoService;
import view.ProdutoView;

import java.util.ArrayList;

public class ProdutoController {
    private ProdutoService service;
    private ProdutoView view;

    public ProdutoController(){
        this.service = new ProdutoService();
        this.view = new ProdutoView();
    }

    public void cadastrar(String nome, double preco, int quantidade){
        String msg = service.cadastrar(nome, preco,quantidade);
        view.exibirMensagem(msg);
    }

    public void listar(){
        ArrayList<Produto> produtos = service.listar();
        view.listarProdutos(produtos);
    }

    public void buscar(String nome){

        view.exibirMensagem("Buscando '" + nome + "':");
        Produto p = service.buscar(nome);
        view.exibirBusca(p);
    }
}
