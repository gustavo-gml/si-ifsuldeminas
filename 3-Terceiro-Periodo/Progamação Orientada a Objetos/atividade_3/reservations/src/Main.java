import entities.Exceptions.ValorInvalidoException;
import entities.Exceptions.VooCanceladoException;
import entities.Exceptions.VooLotadoException;
import entities.Voo;

public class Main{
    static void main(String[] args){
        Voo testes = new Voo(121, 60, 1000.00);

        try {
            testes.reservarAssento(2, 2000);
        } catch (VooLotadoException e) {
            System.out.println(e.getMessage());
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }

        try {
            testes.reservarAssento(2, 1000);
        } catch (VooLotadoException e) {
            System.out.println(e.getMessage());
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }

        try {
            testes.reservarAssento(70, 70000);
        } catch (VooLotadoException e) {
            System.out.println(e.getMessage());
        } catch (ValorInvalidoException e) {
            System.out.println(e.getMessage());
        }

        try {
            testes.cancelarVoo();
        } catch (VooCanceladoException e) {
            System.out.println(e.getMessage());
        }



    }
}