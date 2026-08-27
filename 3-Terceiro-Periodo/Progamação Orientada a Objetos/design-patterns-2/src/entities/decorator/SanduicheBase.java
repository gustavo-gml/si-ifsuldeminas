package entities.decorator;

public class SanduicheBase implements Sanduiche {
    @Override
    public String getDescricao() {
        return "Pão e hambúrguer";
    }

    @Override
    public double getPreco() {
        return 15.00;
    }
}