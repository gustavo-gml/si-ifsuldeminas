package entities;

public class Leitor extends Pessoa{
    private String matricula;

    public Leitor(String nome, String email, String matricula){
        super(nome, email);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return  super.toString() + "\nMatricula = " + matricula;
    }
}
