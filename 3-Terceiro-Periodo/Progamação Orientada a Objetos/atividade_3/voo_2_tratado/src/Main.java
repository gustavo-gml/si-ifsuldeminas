import entities.ReservaAerea;
import entities.exceptions.*;

public class Main{
    public static void main(String[] args){
        ReservaAerea reservaTeste = new ReservaAerea(1,"12345678934", 18, 1000.00, 30, false, false, 2000.0);
        ReservaAerea reservaTeste2 = new ReservaAerea(1,"12345678934", 17, 1000.00, 30, true, false, 200000.0);
        try {
            reservaTeste.reservar(1);
            reservaTeste.reservar(2);


            reservaTeste2.reservar(1);

            reservaTeste2.setIdadePassageiro(18);

            reservaTeste2.reservar(1);
            reservaTeste2.setAssentosDisponiveis(0);
            reservaTeste2.setPassageiroPossuiVisto(true);

            reservaTeste2.reservar(1);
            reservaTeste2.setAssentosDisponiveis(4);

            reservaTeste2.reservar(4);

        }catch (IdadeMaximaException | IdadeMinimaException | SemAssentoException | SaldoInsuficienteException | SemVistoException | CpfInvalidoException | LimiteReservasException | ValorInvalidoException e){
            System.out.println(e.getMessage());
        }


    }

}