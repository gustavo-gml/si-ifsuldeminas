package entities.decorator;

public class Queijo extends SanduicheDecorator {
    public Queijo(Sanduiche sanduiche) {
        super(sanduiche);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", Queijo";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 2.50;
    }
}
