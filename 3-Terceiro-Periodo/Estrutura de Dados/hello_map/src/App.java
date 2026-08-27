import java.util.Scanner;

public class App {
    public static int hash(long key, int tamanho, int deslocamento) {
        return (int) ((key + deslocamento) % tamanho);
    }

    public static void main(String[] args) {
        int tamanho = 10;
        int quant = 0;
        int posicao;
        long[] tabelaHash = new long[tamanho];
        int deslocamento;

        while (true) {
            deslocamento = 0;

            Scanner leia = new Scanner(System.in);
            System.out.println("Digite o CPF: ");
            long cpf = leia.nextLong();

            while (true) {

                posicao = hash(cpf, tamanho, deslocamento);
                
                if (tabelaHash[posicao] == 0) {
                    tabelaHash[posicao] = cpf;
                    quant++;
                    break;
                } else {
                    System.out.println("Colisao - Deslocando");
                    deslocamento++;
                }

                System.out.println("CPF: " + cpf);
                System.out.println("Hash: " + posicao);

                if(quant == tamanho){
                    System.out.println("Sem espaço.");
                    break;
                }
            }

            for (int i = 0; i < tamanho; i++) {
                System.out.print("|" + tabelaHash[i]);
            }
            System.out.println();
        }
    }
}
