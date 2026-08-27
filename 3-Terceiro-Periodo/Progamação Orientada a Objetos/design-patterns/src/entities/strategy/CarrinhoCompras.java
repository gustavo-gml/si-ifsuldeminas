package entities.strategy;

public class CarrinhoCompras {
    private EstrategiaDesconto estrategiaDesconto;
    private double valorTotal;

    public CarrinhoCompras(double valorTotal) {
        this.valorTotal = valorTotal;
        this.estrategiaDesconto = new SemDesconto();
    }

    public void setEstrategiaDesconto(EstrategiaDesconto estrategiaDesconto) {
        this.estrategiaDesconto = estrategiaDesconto;
    }

    public double calcularTotal() {
        return estrategiaDesconto.aplicarDesconto(valorTotal);
    }
}