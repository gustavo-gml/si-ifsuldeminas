package controller;

import model.Aluno;
import service.AlunoService;
import view.AlunoView;

import java.util.ArrayList;

public class AlunoController {
    private AlunoService service;
    private AlunoView view;

    public AlunoController(){
        this.service = new AlunoService();
        this.view = new AlunoView();
    }

    public void cadastrar(String nome, double notaFinal, String matricula){
        String msg = service.cadastrar(nome, notaFinal,matricula);
        view.exibirMensagem(msg);
    }

    public void listar(){
        ArrayList<Aluno> alunos = service.listarTodos();
        view.listarAlunos(alunos);
    }

    public void media(){
        view.exibirMensagem("Média da turma " + String.format("%.2f",service.calcularMedia()));
    }


}
