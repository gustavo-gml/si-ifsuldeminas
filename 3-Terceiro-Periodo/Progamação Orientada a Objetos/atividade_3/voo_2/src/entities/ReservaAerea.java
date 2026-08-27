package entities;




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

    public String reservar(int quantidade) {
        if(this.idadePassageiro < 18){
            return "ERRO_IDADE_MINIMA";
        }
        if(this.idadePassageiro > 100){
            return "ERRO_IDADE_MAXIMA";
        }
        if(this.assentosDisponiveis < quantidade || assentosDisponiveis == 0){
            return "ERRO_SEM_ASSENTO";
        }
        if(this.valorPassagem * quantidade > this.saldoCliente){
            return "ERRO_SALDO_INSUFICIENTE";
        }
        if(this.vooInternacional == true && this.passageiroPossuiVisto == false){
            return "ERRO_SEM_VISTO";
        }
        if(this.cpfPassageiro.length() != 11 || !this.cpfPassageiro.matches("\\d+")){
            return "ERRO_CPF_INVALIDO";
        }
        if(this.valorPassagem <= 0){
            return "ERRO_VALOR_INVALIDO";
        }
        if( this.quantReservas >= 3 || quantidade > 3){
            return "ERRO_LIMITE_RESERVAS";
        }

        this.assentosDisponiveis -= quantidade;
        this.saldoCliente -= this.valorPassagem * quantidade;
        this.quantReservas++;
        return "SUCESSO!";
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
