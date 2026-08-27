package service;

import model.Aluno;
import repository.AlunoRepository;

import java.util.ArrayList;

public class AlunoService {
    private AlunoRepository repository;

    public AlunoService(){
        this.repository = new AlunoRepository();
    }

    public String cadastrar(String nome, double notaFinal, String matricula){
        if (repository.existePorMatricula(matricula) ){
            return "Erro: matrícula já cadastrada";
        }
        if (notaFinal < 0 || notaFinal > 10){
            return "Erro: nota inválida";
        }
        Aluno p = new Aluno(nome, notaFinal, matricula);
        repository.salvar(p);
        return "Sucesso !";
    }

    public ArrayList<Aluno> listarTodos(){
        return repository.listar();
    }

    public double calcularMedia(){
        return repository.calcularMedia();
    }
}
