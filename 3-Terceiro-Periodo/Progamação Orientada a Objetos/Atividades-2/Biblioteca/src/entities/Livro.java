package entities;

import java.util.ArrayList;

public class Livro {
    private String titulo;
    private Autor autor;
    private Integer ano;
    private ArrayList<Livro> livros = new ArrayList<>();;

    public String getTitulo() {
        return titulo;
    }
    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Livro(String titulo, Autor autor, Integer ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        livros.add(new Livro(titulo, autor, ano));
    }

    public Livro(String titulo, Autor autor, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
    }

    public Livro acharLivro(String nome){
        for (Livro l : livros){
            if(nome.equalsIgnoreCase(l.getTitulo())){
                return l;
            }


        }
        return null;
    }

    @Override
    public String toString() {
        return "Titulo = " + titulo +
                "\nAutor = " + autor +
                "\nAno = " + ano;
    }
}