package entities;

import java.time.LocalDate;

public class Emprestimo {
    Livro livro;
    Leitor leitor;
    LocalDate dataEmprestimo;
    LocalDate dataDevolucao;


    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Leitor getLeitor() {
        return leitor;
    }

    public void setLeitor(Leitor leitor) {
        this.leitor = leitor;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }


    public Emprestimo(Livro livro ,Leitor leitor) {
        this.livro = livro;
        this.leitor = leitor;
        this.dataEmprestimo =  LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(7);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "livro=" + livro +
                ", leitor=" + leitor +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
