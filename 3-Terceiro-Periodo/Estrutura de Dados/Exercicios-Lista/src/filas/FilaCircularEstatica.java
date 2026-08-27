package filas;
public class FilaCircularEstatica <T> {
    private int inicio;
    private int fim;
    private int qtd;
    private T[] elementos;

    public FilaCircularEstatica(int tamanho){
        inicio = 0;
        fim = -1;
        qtd = 0;

        elementos = (T[]) new Object[tamanho];
    }

    public boolean isEmpty(){
        return qtd == 0;
    }
    
    // * Questão 15
    public boolean buscarDado(T dadoBusca) {
        int index = inicio;

        for (int i = 0; i < qtd; i++) {
            if (dadoBusca.equals(elementos[index])) {
                return true; // Encontrou!
            }
            // Incremento circular usando o operador de resto da divisão
            index = (index + 1) % elementos.length;
        }
        return false;
    }

// Questão 17
    public T ultimoElemento() {
        if (!isEmpty()) {
            
            return elementos[fim]; 
        }
        return null;
    }

}
