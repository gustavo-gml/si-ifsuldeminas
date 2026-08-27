package entities;
import java.util.ArrayList;
import java.time.LocalTime;

public class Turma {
    private String nome;
    private LocalTime horario;
    private ArrayList<Aluno> alunos;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public Turma(String nome, LocalTime horario) {
        this.nome = nome;
        this.horario = horario;
        this.alunos = new ArrayList<>();

        alunos.add(new Aluno("Preguiça de fazer um construtor melhor", 17,new Bateria("Makita", "De Bater")));
    }


    @Override
    public String toString() {
        return "Nome da Turma: " + nome +
                "\nHorario: " + horario +
                "\nAlunos da Turma:\n" + alunos;
    }
}
