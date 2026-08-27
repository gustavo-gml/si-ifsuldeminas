package repository;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private static List<Produto> estoque = new ArrayList<>();

    public void adicionar(Produto p){
        estoque.add(p);
    }

    public ArrayList<Produto> listar(){
        return new ArrayList<Produto>(estoque);
    }

    public Produto buscarPorNome(String nome){
        for(Produto p : estoque){
            if (nome.equalsIgnoreCase(p.getNome())){
                return p;
            }
        }
        return null;
    }

}
