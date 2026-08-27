package entities;

public abstract class Instrumento {
    private String marca;
    private String tipo;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Instrumento(String marca, String tipo) {
        this.marca = marca;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Marca do Intrumento: " + marca +
                "\nTipo: " + tipo;
    }
}
