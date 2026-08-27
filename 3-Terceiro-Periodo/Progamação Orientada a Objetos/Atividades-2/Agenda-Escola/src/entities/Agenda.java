package entities;

import java.util.ArrayList;

public class Agenda {
    private String nomeAgenda;
    private ArrayList<Contato>  contatos;

    public String getNomeAgenda() {
        return nomeAgenda;
    }

    public void setNomeAgenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
    }

    public ArrayList<Contato> getContatos() {
        return contatos;
    }

    public Agenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
        contatos = new ArrayList<>();
    }

    public void addContato(Contato novoContato){
        this.contatos.add(novoContato);
    }

    @Override
    public String toString() {
        return "Nome da agenda: " + nomeAgenda +
                "\nContatos:\n" + contatos;
    }
}

