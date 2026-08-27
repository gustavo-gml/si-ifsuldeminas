package entities;

import java.util.Iterator;

public class Pilha<T> implements Iterable<T> {

    private int topo;
    private T[] elementos;

    public Pilha(int capacidade){
        this.topo = -1;
        this.elementos = (T[]) new Object[capacidade];
    }

    public boolean isFull(){
        return this.elementos.length -1 == topo;
    }

    public boolean push(T elemento){
        if (!isFull()) {
            this.topo++;
            this.elementos[topo] = elemento;
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return this.topo == -1;
    }

    public T pop(){
        T elementoRemovido;
        if(!isEmpty()){
            elementoRemovido = elementos[topo];
            elementos[topo] = null;
            topo--;
            return elementoRemovido;
        }

        return null;
    }

    public T peak(){
        if(!isEmpty()){
            return elementos[topo];
        }
        return null;
    }

    /*@Override
    public String toString() {
        return "Elementos da pilha: " + Arrays.toString(elementos);
    }*/

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = topo; i >= 0; i--){
            sb.append(elementos[i] + "\n");
        }
        sb.append("____________\n");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator(){
        return new PilhaIterador();
    }


    private class PilhaIterador implements Iterator<T> {
        private int atual = topo;
        public boolean hasNext() {
            return atual >= 0;
        }
        public T next() {
            return elementos[atual--];
        }
    }



}
