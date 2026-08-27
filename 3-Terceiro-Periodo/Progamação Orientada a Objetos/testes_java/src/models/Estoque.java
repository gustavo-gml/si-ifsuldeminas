package models;

import java.util.ArrayList;

public class Estoque {
    private Integer quantidade;

    public Estoque(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void adicionar(int quantidade) throws IllegalArgumentException{
        if (quantidade <= 0){
            throw new IllegalArgumentException("Não é possível adicionar uma quantidade negativa de produtos");
        }
        this.quantidade += quantidade;
    }

    public void remover(int quantidade) throws IllegalArgumentException{
        if (quantidade <= 0){
            throw new IllegalArgumentException("Não é possível remover uma quantidade negativa de produtos");
        }

        if (quantidade > this.quantidade){
            throw new IllegalArgumentException("Não é possível remover uma quantidade de produdos maior que a disponível");
        }
        this.quantidade -= quantidade;
    }
}
