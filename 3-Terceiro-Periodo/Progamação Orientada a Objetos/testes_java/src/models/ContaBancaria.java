package models;

public class ContaBancaria {
    private Double saldo;
    private String setor = "Setor Padrão";

    public ContaBancaria(double valorInicial){
        if (valorInicial < 0){
            throw new IllegalArgumentException("Não é possivel inicializar uma conta com valor negativo");
        }
        this.saldo = valorInicial;
    }
    public double getSaldo(){
        return saldo;
    }


    public void depositar(double valor) throws IllegalArgumentException{
        if (valor <= 0){
            throw new IllegalArgumentException("Não é possível fazer depósitos negativos");
        }
        this.saldo += valor;
    }

    public void saque(double valor) throws IllegalStateException {
        if(valor > saldo){
            throw new IllegalStateException("O valor do saldo é insuficiente");
        }
        if(valor <= 0){
            throw new IllegalArgumentException("Não é possivel sacar um valor negativo");
        }

        this.saldo -= valor;
    }


}
