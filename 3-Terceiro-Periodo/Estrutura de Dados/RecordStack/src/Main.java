import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main{
    private static int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Menu Pilha de Recordes ---");
        System.out.println("1. Inserir novo recorde - Push");
        System.out.println("2. Consultar recorde do topo - Peek");
        System.out.println("3. Remover recorde do topo - Pop");
        System.out.println("4. Mostrar pilha");
        System.out.println("0. Sair");
        System.out.printf("Digite a opção desejada: " );
        return Integer.parseInt(sc.nextLine());
    }
    static void main(String[] args){
        System.out.print("Qual o tamanho da pilha de recordes ? ");
        Scanner sc = new Scanner(System.in);
        int capacity = Integer.parseInt(sc.nextLine());

        Stack<Record> minhaPilha = new Stack<>(capacity);

        int option = 0;

        do {
            option = menu();

            switch (option){
                case 1:
                    System.out.print("Informe a data do recorde (dd/MM/AAAA): ");
                    String dataString = sc.nextLine();
                    DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate data = LocalDate.parse(dataString, fmt1);

                    System.out.print("Tempo do recorde: ");
                    double tempo = Double.parseDouble(sc.nextLine());

                    System.out.print("Nome do recordista: ");
                    String nome = sc.nextLine();


                    Record novoRecorde = new Record(data, tempo, nome);

                    if (minhaPilha.isEmpty()){
                        minhaPilha.push(novoRecorde);
                        System.out.println("Dado inserido!");
                    }
                    else if(minhaPilha.peak().getTime() > novoRecorde.getTime()){
                        if (minhaPilha.push(novoRecorde)){
                            System.out.println("Dado inserido!");
                        }
                        else{
                            System.out.println("Pilha cheia - Redimencionando...");
                            minhaPilha.resize();
                            minhaPilha.push(novoRecorde);
                            System.out.println("Dado inserido!");
                        }
                    } else{
                        System.out.println("O recorde informado é maior do que o atual!");
                    }
                    break;

                case 2:
                    if (minhaPilha.isEmpty()){
                        System.out.println("A pilha está vazia");
                    }else {
                        System.out.println("Elemento do topo: " + minhaPilha.peak());
                    }

                break;

                case 3:
                    if (!minhaPilha.isEmpty()){
                        System.out.println("'"+minhaPilha.pop()+"' removido com sucesso!");
                    }else{
                        System.out.println("A pilha está vazia !");
                    }
                    break;

                case 4:
                    System.out.println(minhaPilha);
            }

        } while (option != 0);
    }
}