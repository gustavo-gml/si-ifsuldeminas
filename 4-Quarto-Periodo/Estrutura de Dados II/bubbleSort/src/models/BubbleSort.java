package models;

import java.util.*;

public class BubbleSort<T extends Comparable<T>> {

    public T[] sort(T[] array, Comparator<T> comparator) {
        int n = array.length;

        for (int fase = 1; fase < n; fase++) {
            for (int j = 0; j < n - fase; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    troca(array, j, j + 1);
                }
            }
        }

        return array;
    }

    public T[] sort(T[] array) {
        return sort(array, (a, b) -> ((Comparable<T>) a).compareTo(b));
    }

    private void troca(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // sort verboso
    public T[] sortVerbose(T[] array, Comparator<T> comparator) {
        int n = array.length;
        int comparacoes = 1;

        for (int fase = 1; fase < n; fase++) {
            System.out.println("Fase " + fase + ": " + Arrays.toString(array));

            for (int j = 0; j < n - fase; j++) {

                System.out.println(comparacoes + " Comparação: " + array[j] + " e " + array[j + 1]);
                comparacoes++;

                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    System.out.println("Trocando: " + array[j] + " e " + array[j + 1]);
                    troca(array, j, j + 1);
                    System.out.println("Resultado: " + Arrays.toString(array));
                }
            }
        }
        int totalcomp = comparacoes - 1;
        System.out.println("Total de comparações: " + totalcomp);
        return array;
    }// fim sortVerbose

    public T[] sortVerbose(T[] array) {
        return sortVerbose(array, (a, b) -> ((Comparable<T>) a).compareTo(b));
    }

}