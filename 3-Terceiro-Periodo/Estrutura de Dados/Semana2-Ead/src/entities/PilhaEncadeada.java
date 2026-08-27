package entities;

import java.util.Iterator;

public class PilhaEncadeada<T> implements Iterable{
    private No<T> topo;
    private int size;

    public PilhaEncadeada(){
        this.topo = null;
        this.size = 0;
    }

    public boolean isEmpty(){
        return this.topo == null;
    }

    public boolean push(T dado){
        No<T> novoNo = new No<>(dado);
        novoNo.setProximo(this.topo);
        this.topo = novoNo;
        this.size++;
        return true;
    }

    public T pop(){
        T elementoRemovido;
        if(!isEmpty()){
            elementoRemovido = this.topo.getDado();
            this.topo = this.topo.getProximo();
            this.size--;
            return elementoRemovido;
        }

        return null;
    }

    public T peak(){
        if(!isEmpty()){
            return this.topo.getDado();
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        No<T> noAtual = topo;

        while (noAtual != null){
            sb.append(noAtual.getDado() + "\n");
            noAtual = noAtual.getProximo();
            sb.append("____________\n");
        }


        return sb.toString();
    }

    @Override
    public Iterator<T> iterator(){
        return new PilhaIterador();
    }


    private class PilhaIterador implements Iterator<T> {
        private No<T> noAtual = topo;
        public boolean hasNext() {
            return noAtual != null;
        }
        public T next() {
            T dado = noAtual.getDado();
            noAtual = noAtual.getProximo();
            return dado;
        }

    }
}
