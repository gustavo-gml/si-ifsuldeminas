public class Documento {
    private String cpf;
    private String rg;
    private String cnh;
    private String tituloEleitor;

    public Documento(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Documento documento = (Documento) o;
        return this.cpf.equals(documento.cpf);
    }   
    @Override
    public int hashCode() {
        return this.cpf.hashCode();
    }

    @Override
    public String toString() {
        return "Documento{" +
                "cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", cnh='" + cnh + '\'' +
                ", tituloEleitor='" + tituloEleitor + '\'' +
                '}';
    }

}
