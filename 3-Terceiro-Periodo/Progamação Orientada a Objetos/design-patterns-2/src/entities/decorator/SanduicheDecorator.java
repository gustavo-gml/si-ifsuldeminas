package entities.decorator;

public abstract class SanduicheDecorator implements Sanduiche {
    protected Sanduiche sanduiche;

    public SanduicheDecorator(Sanduiche sanduiche) {
        this.sanduiche = sanduiche;
    }

    @Override
    public String getDescricao() {
        return sanduiche.getDescricao();
    }

    @Override
    public double getPreco() {
        return sanduiche.getPreco();
    }
}