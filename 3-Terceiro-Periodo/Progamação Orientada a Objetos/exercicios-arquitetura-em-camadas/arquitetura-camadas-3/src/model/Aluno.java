package model;

public class Aluno {
    private String nome;
    private Double notaFinal;
    private String matricula;

    public Aluno(String nome, double notaFinal, String matricula) {
        this.nome = nome;
        this.notaFinal = notaFinal;
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString(){
        return nome + " (" + matricula + ") - " + notaFinal;
    }
}


