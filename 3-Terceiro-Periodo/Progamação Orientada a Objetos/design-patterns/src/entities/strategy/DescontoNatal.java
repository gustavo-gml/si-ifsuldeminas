package entities.strategy;

public class DescontoNatal implements EstrategiaDesconto {
    @Override
    public double aplicarDesconto(double valorOriginal) {
        return valorOriginal * 0.80;
    }
}