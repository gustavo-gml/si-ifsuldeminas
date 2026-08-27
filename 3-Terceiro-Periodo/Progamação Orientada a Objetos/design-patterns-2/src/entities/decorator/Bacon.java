package entities.decorator;

public class Bacon extends SanduicheDecorator {
    public Bacon(Sanduiche sanduiche) {
        super(sanduiche);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", Bacon";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 3.50;
    }
}