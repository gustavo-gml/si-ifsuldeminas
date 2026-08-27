

import java.util.Scanner;

import filas.FilaDinamica;
import filas.No;
import pilhas.Pilha;
import pilhas.PilhaEncadeada;

public class Main {

    public boolean ordemCrescente(){
        FilaDinamica<Integer> filaInt = new FilaDinamica<>();


            if (filaInt.isEmpty() || filaInt.getInicio().getProximo() == null) return true;

            No aux = filaInt.getInicio();
            while (aux.getProximo() != null) {
                
                if ((int) aux.getDado() > (int) aux.getProximo().getDado()) {
                    return false;
                }
                aux = aux.getProximo();
            }
            return true;
        }


    public static void main(String[] args) {

        PilhaEncadeada<Long> minhaPilha = new PilhaEncadeada<>();

        Scanner sc = new Scanner(System.in);

        System.out.print("Digite um número decimal para conversão: ");
        long decimal = Long.parseLong(sc.nextLine());
        System.out.print("Digite uma base de 2 a 8: ");
        int base = Integer.parseInt(sc.nextLine());

        long numero = decimal;

        while (numero > 0){
            long resto = numero % base;
            minhaPilha.push(resto);
            numero = numero / base;
        }

        System.out.print("\nResultado: ");

        while (!minhaPilha.isEmpty()){
            System.out.print(minhaPilha.pop());
        }

    }
}
