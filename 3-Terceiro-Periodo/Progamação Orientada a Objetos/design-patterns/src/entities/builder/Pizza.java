package entities.builder;

public class Pizza {
    private final String tamanho;
    private final boolean queijoExtra;
    private final boolean bordaRecheada;
    private final boolean molhoEspecial;

    private Pizza(Builder builder) {
        this.tamanho = builder.tamanho;
        this.queijoExtra = builder.queijoExtra;
        this.bordaRecheada = builder.bordaRecheada;
        this.molhoEspecial = builder.molhoEspecial;
    }

    public String getTamanho() { return tamanho; }
    public boolean temQueijoExtra() { return queijoExtra; }
    public boolean temBordaRecheada() { return bordaRecheada; }
    public boolean temMolhoEspecial() { return molhoEspecial; }

    public static class Builder {
        private final String tamanho;
        private boolean queijoExtra = false;
        private boolean bordaRecheada = false;
        private boolean molhoEspecial = false;

        public Builder(String tamanho) {
            this.tamanho = tamanho;
        }

        public Builder adicionarQueijoExtra() {
            this.queijoExtra = true;
            return this;
        }

        public Builder adicionarBordaRecheada() {
            this.bordaRecheada = true;
            return this;
        }

        public Builder adicionarMolhoEspecial() {
            this.molhoEspecial = true;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }
}