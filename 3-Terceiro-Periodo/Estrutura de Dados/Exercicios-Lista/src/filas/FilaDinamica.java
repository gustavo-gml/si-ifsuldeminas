package filas;

public class FilaDinamica <T> {
    private No<T> inicio;
    private No<T> fim;
    private int qtd;

    public FilaDinamica(){
        inicio = null;
        fim = null;
        qtd = 0;
    }

    public boolean isEmpty(){
        return inicio == null;
    }

    
// * Questão 16
    public int posicaoDado(T dadoBusca) {
        No<T> aux = inicio;
        int count = 1;

        while (aux != null) {
            if (aux.getDado().equals(dadoBusca)) {
                return count;
            }
            aux = aux.getProximo();
            count++;
        }

        return -1;
    }

// Questão 17
    public T ultimoElemento() {
        if (!isEmpty()) {
            return fim.getDado();
        }
        return null;
    }

// Questão 18
    public int quantidadeFila() {
        return qtd;
    }

    // Questão 19


    public No<T> getInicio() {
        return inicio;
    }

    public No<T> getFim() {
        return fim;
    }

    public int getQtd() {
        return qtd;
    }
}
