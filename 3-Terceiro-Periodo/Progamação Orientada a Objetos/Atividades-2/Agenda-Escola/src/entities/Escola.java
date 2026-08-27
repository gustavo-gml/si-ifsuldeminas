package entities;

import java.util.ArrayList;

public class Escola {
    private String nomeEscola;
    private ArrayList<Turma> turmas;

    public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }

    public ArrayList<Turma> getTurmas() {
        return turmas;
    }

    public Escola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
        this.turmas = new ArrayList<>();
    }

    public void addTurma(Turma novaTurma){
        this.turmas.add(novaTurma);
    }

    @Override
    public String toString() {
        return
                "Nome da escola: " + nomeEscola +
                "\nTurmas\n: " + turmas;
    }
}
