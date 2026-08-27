import java.util.Scanner;

public class App {

    public static long pot(long a, long b){
        if(b == 0){
            System.out.println("Caso base\n1");
            return 1;
        }
        long retorno = a * pot(a,b-1);
        System.out.println("Instancia fat(" +a+"^"+b+") = "+ retorno);
        return retorno;
    }
    public static long fatorial(long n) {
        if (n == 0) {
            return 1;
        }
        return n * fatorial(n - 1);
    }

    public static long fatorial(long n, boolean verbose) {
        if (verbose) {
            System.out.println("fat("+n+")");
            if (n == 0) {
                System.out.println("Caso base\n1");
                return 1;
            }
            long retorno = n * fatorial(n - 1, true);
            System.out.println("Instancia fat(" +n+ ") = "+ retorno);
            return retorno;
        }
        else
            return fatorial(n);
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        //System.out.println(fatorial(sc.nextLong(), true));

        System.out.println(pot(10, 5));
    }
}
