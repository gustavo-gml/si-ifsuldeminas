package entities;

public class Processo {
    private String descricao;
    private double tempo;

    public Processo(String descricao, double tempo) {
        this.descricao = descricao;
        this.tempo = tempo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        this.tempo = tempo;
    }

    @Override
    public String toString() {
        return this.descricao + " | " + this.tempo;
    }
}
