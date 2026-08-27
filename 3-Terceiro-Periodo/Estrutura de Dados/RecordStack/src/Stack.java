public class Stack <T> {
    private int top;
    private T[] elements;

    public Stack(int capacity){
        this.top = -1;
        this.elements = (T[]) new Object[capacity];
    }

    public boolean isFull(){
        return this.elements.length -1 == top;
    }

    public boolean push(T elemento){
        if (!isFull()) {
            this.top++;
            this.elements[top] = elemento;
            return true;
        }
        return false;
    }

    public boolean isEmpty(){
        return this.top == -1;
    }

    public T pop(){
        T elementoRemovido;
        if(!isEmpty()){
            elementoRemovido = elements[top];
            elements[top] = null;
            top--;
            return elementoRemovido;
        }

        return null;
    }

    public T peak(){
        if(!isEmpty()){
            return elements[top];
        }
        return null;
    }

    public void resize(){

        int currentSize = 5 ; //caso o usuario tenha declarado um vetor == 0

        if(elements.length != 0){
            currentSize = elements.length;
        }

        T[] newStack = (T[])  new Object[(currentSize * 2)];

        for (int i = 0; i < elements.length; i++){
            newStack[i] = elements[i];
        }

        elements = newStack;
        System.out.println("Pilha redimencionada com sucesso!");

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int pos = 1;
        for (int i = top; i >= 0; i--){
            sb.append(pos +"º lugar\n");
            sb.append(elements[i] + "\n");
            pos++;
            sb.append("\n");
        }
        sb.append("____________\n");
        return sb.toString();
    }
}
