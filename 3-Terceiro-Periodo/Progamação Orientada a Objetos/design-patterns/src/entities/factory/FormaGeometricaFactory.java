package entities.factory;

public class FormaGeometricaFactory {
    public FormaGeometrica criarForma(String tipo) {
        if (tipo == null) {
            return null;
        }

        if (tipo.equalsIgnoreCase("CIRCULO")) {
            return new Circulo();
        } else if (tipo.equalsIgnoreCase("QUADRADO")) {
            return new Quadrado();
        } else if (tipo.equalsIgnoreCase("TRIANGULO")) {
            return new Triangulo();
        }

        return null;
    }
}