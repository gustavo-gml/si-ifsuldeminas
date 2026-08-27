package entities;

public class jogador {
    private String nome;

    public jogador(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "jogador{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
