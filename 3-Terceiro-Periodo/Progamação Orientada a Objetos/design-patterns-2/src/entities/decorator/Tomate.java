package entities.decorator;

public class Tomate extends SanduicheDecorator {
    public Tomate(Sanduiche sanduiche) {
        super(sanduiche);
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + ", Tomate";
    }

    @Override
    public double getPreco() {
        return super.getPreco() + 1.00;
    }
}