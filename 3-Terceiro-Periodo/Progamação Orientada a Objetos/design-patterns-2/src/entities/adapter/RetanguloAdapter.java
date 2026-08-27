package entities.adapter;

public class RetanguloAdapter implements AreaCalculavel {
    private CalculadoraDeAreaRetangulo calculadoraAntiga;
    private double largura;
    private double altura;

    public RetanguloAdapter(CalculadoraDeAreaRetangulo calculadoraAntiga, double largura, double altura) {
        this.calculadoraAntiga = calculadoraAntiga;
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    public double calcularArea() {
        return calculadoraAntiga.calcularAreaAntiga(largura, altura);
    }
}