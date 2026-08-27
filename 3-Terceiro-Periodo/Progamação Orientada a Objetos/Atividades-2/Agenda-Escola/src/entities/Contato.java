package entities;

public class Contato {
    private String nome;
    private String telefone;

    public String getNome(){
        return this.nome;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public Contato(String nome, String telefone){
        this.nome = nome;
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return "Nome: " + this.nome + "\nTelefone: " + this.telefone +"\n";
    }
}
