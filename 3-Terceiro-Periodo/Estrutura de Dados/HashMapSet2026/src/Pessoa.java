import java.time.LocalDate;

public class Pessoa {
    private String nome;
    private Documento documento;
    private LocalDate dataNascimento;

    public Pessoa(String nome, Documento documento, LocalDate dataNascimento) {
        this.nome = nome;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return documento.getCpf();
    }

    public void setCpf(String cpf) {
        documento.setCpf(cpf);
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return this.documento.equals(pessoa.documento);
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", cpf='" + documento.getCpf() + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
