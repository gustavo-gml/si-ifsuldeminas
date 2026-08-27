package entities;

import entities.Exceptions.ValorInvalidoException;
import entities.Exceptions.VooCanceladoException;
import entities.Exceptions.VooLotadoException;

public class Voo {
    private Integer numeroVoo;
    private Integer assentosDisponiveis;
    private Double valorPassagem;

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

    public Voo(Integer numeroVoo, Integer assentosDisponiveis, Double valorPassagem) {
        this.numeroVoo = numeroVoo;
        this.assentosDisponiveis = assentosDisponiveis;
        this.valorPassagem = valorPassagem;
    }

    public void reservarAssento(int quantidade, double valorPago) throws ValorInvalidoException, VooLotadoException{
        if(quantidade <= 0){
            throw new ValorInvalidoException("Não é possivel comprar com quantidade inferior ou igual a zero");
        }
        if(quantidade > this.assentosDisponiveis){
            throw new VooLotadoException("Não há essa quantidade de assentos disponiveis no momento.");
        }
        if(valorPago < quantidade * this.valorPassagem){
            throw new ValorInvalidoException("Pagamento insuficiente para a quantidade adquirida de passagens.");
        }

        this.assentosDisponiveis -= quantidade;
        System.out.println("Reserva realizada com sucesso!");
    }

    public void cancelarVoo() throws VooCanceladoException{
        throw new VooCanceladoException("O voo foi cancelado.");
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
