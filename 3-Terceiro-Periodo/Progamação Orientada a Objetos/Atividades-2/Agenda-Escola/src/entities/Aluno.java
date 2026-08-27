package entities;

public class Aluno {
    private String nome;
    private Integer idade;
    private Instrumento instrumento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Instrumento getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }

    public Aluno(String nome, Integer idade, Instrumento instrumento) {
        this.nome = nome;
        this.idade = idade;
        this.instrumento = instrumento;
    }

    @Override
    public String toString() {
        return "Nome do aluno: " + nome +
                "\nIdade: " + idade +
                "\nInstrumento:\n" + instrumento +"\n";
    }
}
