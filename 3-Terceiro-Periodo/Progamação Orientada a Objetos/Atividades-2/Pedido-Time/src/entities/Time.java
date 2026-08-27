package entities;

import java.util.ArrayList;

public class Time {
    private ArrayList<jogador> jogadores;
    private String nome;

    public Time(String nome) {
        this.nome = nome;
        jogadores = new ArrayList<>();
    }

    public void addJogador(jogador jogador){
        jogadores.add(jogador);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome do time: " + this.nome + "\n");
        for (jogador j : jogadores){
            sb.append(j.toString());
        }
        return sb.toString();
    }
}
