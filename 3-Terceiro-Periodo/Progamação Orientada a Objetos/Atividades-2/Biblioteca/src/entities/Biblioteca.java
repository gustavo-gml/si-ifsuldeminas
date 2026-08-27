package entities;

import java.util.ArrayList;

public class Biblioteca {
    private String nome;
    private ArrayList<Livro> livros;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Leitor> leitores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }


    public ArrayList<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public ArrayList<Leitor> getLeitores() {
        return leitores;
    }

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.leitores = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro){
        livros.add(livro);
    }

    public void adicionarEmprestimo(Emprestimo emprestimo){
        emprestimos.add(emprestimo);
    }

    public void adicionarLeitores(Leitor leitor){
        leitores.add(leitor);
    }

    public Livro acharLivro(String nome){
        for (Livro l : livros){
            if(nome.equalsIgnoreCase(l.getTitulo())){
                return l;
            }


        }
        return null;
    }

    public Leitor acharLeitor(String matricula){

        for (Leitor l : leitores){
            if(matricula.equalsIgnoreCase(l.getMatricula())){
                return l;
            }


        }
        return null;
    }
}
