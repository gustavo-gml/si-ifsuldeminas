
import models.*;

import java.time.LocalDate;
import java.util.Arrays;

public class App {
    public static Pessoa[] vetorDePessoas() {
        return new Pessoa[] {
                new Pessoa("João", LocalDate.of(1990, 1, 1)),
                new Pessoa("Ana", LocalDate.of(1992, 2, 2)),
                new Pessoa("Maria", LocalDate.of(1995, 5, 5)),
                new Pessoa("Pedro", LocalDate.of(1993, 4, 4)),
                new Pessoa("Lucas", LocalDate.of(1991, 6, 6)),
                new Pessoa("Carlos", LocalDate.of(1988, 3, 3))
        };
    }

    public static void main(String[] args) {
        BubbleSort<Pessoa> bubbleSortPessoas = new BubbleSort<>();
        Pessoa[] pessoas = vetorDePessoas();
        bubbleSortPessoas.sort(pessoas);
        System.out.println(Arrays.toString(pessoas));
    }
}
