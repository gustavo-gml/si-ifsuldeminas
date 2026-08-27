package models;

public class Calculadora {
    public int somar (int a, int b){
        return a+b;
    }

    public int subtrair (int a, int b){
        return a-b;
    }

    public int multiplicar (int a, int b){
        return a*b;
    }

    public int dividir  (int a, int b) throws IllegalArgumentException{
        if(b == 0){
            throw new IllegalArgumentException("Zero não pode");
        }
        return a/b;
    }


}
