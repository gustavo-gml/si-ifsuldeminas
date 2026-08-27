import entities.Pilha;
import entities.PilhaEncadeada;
import entities.Processo;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    private static int menu() {
        System.out.println("\n--- Menu entities.Pilha ---");
        System.out.println("1. Inserir elemento - Push");
        System.out.println("2. Remover elemento - Pop");
        System.out.println("3. Mostrar topo - Peek");
        System.out.println("4. Mostrar pilha");
        System.out.println("0. Sair");
        System.out.printf("Digite a opção desejada:" );
        return Integer.parseInt(sc.nextLine());
    }
    public static void main(String[] args) throws Exception {

        PilhaEncadeada<Processo> minhaPilha = new PilhaEncadeada<>();

        int opcao = 0;

        do {
            opcao = menu();
            switch(opcao){
                case 1:
                    System.out.print("Digite a descrição do processo: ");
                    String descricao = sc.nextLine();

                    System.out.print("Digite o tempo do processo: ");
                    double tempo = Double.parseDouble(sc.nextLine());



                    if(minhaPilha.push(new Processo(descricao, tempo))) // ==true
                        System.out.println("Dado inserido!");
                    else
                        System.out.println("Pilha cheia - Stack Overflow");
                    break;

                case 2:
                    if (!minhaPilha.isEmpty()){
                        System.out.println("'"+minhaPilha.pop()+"' removido com sucesso!");
                    }else{
                        System.out.println("Pilha vazia !");
                    }
                    break;

                case 3:
                    if (minhaPilha.isEmpty()){
                        System.out.println("A pilha está vazia");
                    }else {
                        System.out.println("Elemento do topo: " + minhaPilha.peak());
                    }
                    break;
                case 4:
                    for (Object p : minhaPilha){
                        System.out.println(p);
                    }
                    break;
                case 0: System.out.println("Saindo...");
                    break;
            }// fim switch


        } while (opcao!=0);



    }
}
