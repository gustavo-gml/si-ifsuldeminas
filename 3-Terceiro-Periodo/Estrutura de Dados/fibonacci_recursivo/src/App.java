import java.util.Scanner;

public class App {

        public static int fibIterativo(int n) {
        if (n <= 0) return 0;

        int i, atual = 1, prox, ant = 0;
        
        for (i = 2; i <= n; i++) {
            System.out.print(atual + " - ");
            
            prox = atual + ant;
            ant = atual;
            atual = prox;

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    
        System.out.println(atual); 
        
        return atual; 
    }

    public static int fib(int n){
        //fn = f(n-1) + f(n-2)
        if (n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }

        return fib(n - 1) + fib(n - 2);

    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Usando recursão:");
        System.out.println(fib(sc.nextInt()));

        System.out.println("Usando Loops: ");
        System.out.println(fibIterativo(sc.nextInt()));
    }
}
