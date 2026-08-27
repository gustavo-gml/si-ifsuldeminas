package view;

import model.Aluno;

import java.util.ArrayList;

public class AlunoView {
    public void exibirMensagem(String msg){
        System.out.println(msg);
    }

    public void listarAlunos(ArrayList<Aluno> alunos){
        System.out.println("Lista de alunos: ");
        for(Aluno p : alunos){
            System.out.println(p);
        }
    }
}
