package service;

import model.Produto;
import repository.ProdutoRepository;

import java.util.ArrayList;

public class ProdutoService {
    private ProdutoRepository repository;

    public ProdutoService(){
        this.repository = new ProdutoRepository();
    }

    public String cadastrar(String nome, double preco, int quantidade){
        Produto p = new Produto(nome, preco, quantidade);
        repository.adicionar(p);
        return "Cadastro realizado com sucesso ! \nDados inseridos: " + p;
    }

    public ArrayList<Produto> listar(){
        return repository.listar();
    }

    public Produto buscar(String nome){
        return repository.buscarPorNome(nome);
    }
}
