import entities.ReservaAerea;

public class Main{
    public static void main(String[] args){
        ReservaAerea reservaTeste = new ReservaAerea(1,"12345678934", 18, 1000.00, 30, false, false, 2000.0);
        System.out.println(reservaTeste.reservar(1));
        System.out.println(reservaTeste.reservar(2) + "\n");

        ReservaAerea reservaTeste2 = new ReservaAerea(1,"12345678934", 17, 1000.00, 30, true, false, 200000.0);
        System.out.println(reservaTeste2.reservar(1));

        reservaTeste2.setIdadePassageiro(18);

        System.out.println(reservaTeste2.reservar(1));
        reservaTeste2.setAssentosDisponiveis(0);
        reservaTeste2.setPassageiroPossuiVisto(true);

        System.out.println(reservaTeste2.reservar(1));
        reservaTeste2.setAssentosDisponiveis(4);

        System.out.println(reservaTeste2.reservar(4));

    }

}