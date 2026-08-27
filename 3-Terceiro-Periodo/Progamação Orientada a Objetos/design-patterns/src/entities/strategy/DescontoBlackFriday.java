package entities.strategy;

public class DescontoBlackFriday implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valorOriginal) {
        return valorOriginal * 0.70;
    }
}
