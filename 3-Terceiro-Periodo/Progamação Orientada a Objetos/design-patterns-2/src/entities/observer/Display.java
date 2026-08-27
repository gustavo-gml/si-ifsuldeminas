package entities.observer;

public class Display implements Observer {
    private String nome;

    public Display(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(float temperatura, float umidade, float pressao) {
        System.out.println(nome + " - Temp: " + temperatura + "C, Umidade: " + umidade + "%, Pressão: " + pressao + "hPa");
    }
}
