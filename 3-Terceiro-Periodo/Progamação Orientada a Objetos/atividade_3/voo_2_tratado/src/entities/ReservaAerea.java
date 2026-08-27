package entities;


import entities.exceptions.*;

public class ReservaAerea {
    private Integer numeroVoo;
    private String cpfPassageiro;
    private Integer idadePassageiro;
    private Double valorPassagem;
    private Integer assentosDisponiveis;
    private Boolean vooInternacional;
    private Boolean passageiroPossuiVisto;
    private Double saldoCliente;
    private Integer quantReservas;

    public String getCpfPassageiro() {
        return cpfPassageiro;
    }

    public boolean isVooInternacional() {
        return vooInternacional;
    }

    public void setVooInternacional(boolean vooInternacional) {
        this.vooInternacional = vooInternacional;
    }

    public void setCpfPassageiro(String cpfPassageiro) {
        this.cpfPassageiro = cpfPassageiro;
    }

    public int getIdadePassageiro() {
        return idadePassageiro;
    }

    public void setIdadePassageiro(int idadePassageiro) {
        this.idadePassageiro = idadePassageiro;
    }

    public boolean isPassageiroPossuiVisto() {
        return passageiroPossuiVisto;
    }

    public void setPassageiroPossuiVisto(boolean passageiroPossuiVisto) {
        this.passageiroPossuiVisto = passageiroPossuiVisto;
    }

    public double getSaldoCliente() {
        return saldoCliente;
    }

    public void setSaldoCliente(double saldoCliente) {
        this.saldoCliente = saldoCliente;
    }

    public Integer getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public Integer getNumeroVoo() {
        return numeroVoo;
    }

    public void setNumeroVoo(Integer numeroVoo) {
        this.numeroVoo = numeroVoo;
    }

    public Double getValorPassagem() {
        return valorPassagem;
    }

    public void setValorPassagem(Double valorPassagem) {
        this.valorPassagem = valorPassagem;
    }

    public void setAssentosDisponiveis(Integer assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public ReservaAerea(Integer numeroVoo, String cpfPassageiro, Integer idadePassageiro, Double valorPassagem, Integer assentosDisponiveis, Boolean vooInternacional, Boolean passageiroPossuiVisto, Double saldoCliente) {
        this.numeroVoo = numeroVoo;
        this.cpfPassageiro = cpfPassageiro;
        this.idadePassageiro = idadePassageiro;
        this.valorPassagem = valorPassagem;
        this.assentosDisponiveis = assentosDisponiveis;
        this.vooInternacional = vooInternacional;
        this.passageiroPossuiVisto = passageiroPossuiVisto;
        this.saldoCliente = saldoCliente;
        this.quantReservas = 0;
    }

    public void reservar(int quantidade) {
        if(this.idadePassageiro < 18){
            throw new IdadeMinimaException("A idade do passageiro deve ser acima de 18 anos");
        }
        if(this.idadePassageiro > 100){
            throw new IdadeMaximaException("O passaageiro provavelmente não existe");
        }
        if(this.assentosDisponiveis < quantidade || assentosDisponiveis == 0){
            throw new SemAssentoException("A quantidade de assentos disponiveis é inferior a quantidade da compra");
        }
        if(this.valorPassagem * quantidade > this.saldoCliente){
            throw new SaldoInsuficienteException("Saldo insuficiente para realizar a compra.");
        }
        if(this.vooInternacional == true && this.passageiroPossuiVisto == false){
            throw new SemVistoException("É preciso emissão visto para voos internacionais");
        }
        if(this.cpfPassageiro.length() != 11 || !this.cpfPassageiro.matches("\\d+")){
            throw new CpfInvalidoException("O CPF cadastrado é inválido. São necessarios exatamente 11 digitos numericos");
        }
        if(this.valorPassagem <= 0){
            throw new ValorInvalidoException("O valor da passagem não pode ser negativo");
        }
        if( this.quantReservas >= 3 || quantidade > 3){
            throw  new LimiteReservasException("Só é possível realizar três reservas por CPF");
        }

        this.assentosDisponiveis -= quantidade;
        this.saldoCliente -= this.valorPassagem * quantidade;
        this.quantReservas++;
        System.out.println("Reserva realizada com sucesso");
    }


    @Override
    public String toString() {
        return "Voo{" +
                "numeroVoo=" + numeroVoo +
                ", assentosDisponiveis=" + assentosDisponiveis +
                ", valorPassagem=" + valorPassagem +
                '}';
    }
}
