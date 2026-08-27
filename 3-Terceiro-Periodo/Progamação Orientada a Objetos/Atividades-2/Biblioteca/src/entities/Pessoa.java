package entities;

public abstract class Pessoa {
    private String nome;
    private String email;

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa(String email, String nome){
        this.nome = nome;
        this.email = email;
    }


    public Pessoa( String nome){
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Nome = " + nome +" Email = " + email;
    }
}
