package entities;

import entities.exceptions.ContaInativaException;
import entities.exceptions.SaldoInsuficienteException;
import entities.exceptions.ValorInvalidoException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ContaBancaria {
    private Integer numeroConta;
    private String titular;
    private Double saldo;

    @Override
    public String toString() {
        return "Saldo: " + saldo +
                "\nTitular da conta: " + titular  +
                "\nNumero da conta: " + numeroConta;
    }

    private Boolean ativa;

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }


    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public ContaBancaria(Integer number, String titular, Double saldoInicial) {
        this.numeroConta = number;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
    }

    public void deposit(double amount) throws  ValorInvalidoException, ContaInativaException{
        if(amount <= 0 ){
            throw  new ValorInvalidoException("Não é possivel depositar um valor negativo.");
        }
        if (this.ativa == false){
            throw  new ContaInativaException("Não é possivel realizar operações com contas inativas");
        }

        this.saldo += amount;
    }

    public void withdraw(double amount) throws SaldoInsuficienteException,ValorInvalidoException, ContaInativaException {
        if (amount > this.saldo){
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }
        if(amount <= 0 ){
            throw  new ValorInvalidoException("Não é possivel sacar um valor negativo.");
        }
        if (this.ativa == false){
            throw  new ContaInativaException("Não é possivel realizar operações com contas inativas");
        }
        System.out.println("debug teste");
        this.saldo -= amount;

    }

}
