package repository;

import model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoRepository {
    private static List<Aluno> alunos = new ArrayList<>();

    public void salvar(Aluno p){
        alunos.add(p);
    }

    public ArrayList<Aluno> listar(){
        return new ArrayList<Aluno>(alunos);
    }

    public boolean existePorMatricula(String matricula){
        for(Aluno p : alunos){
            if (matricula.equalsIgnoreCase(p.getMatricula())){
                return true;
            }
        }
        return false;
    }

    public double calcularMedia(){
        double soma = 0;
        for (Aluno a : alunos){
            soma += a.getNotaFinal();
        }
        return soma / alunos.size();
    }

}
