package entities;

public class Autor extends Pessoa{
    private String biografia;

    public Autor(String nome, String email, String biografia){
        super(email, nome);
        this.biografia = biografia;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return  super.toString() + "\nBiografia = " + biografia;
    }
}
