import entities.*;

import java.util.Scanner;

public class Main{

    public static Scanner sc = new Scanner(System.in);

    public static int menu(){
        System.out.print("---Menu Biblioteca---\n");
        System.out.println("Selecione uma opção: ");
        System.out.println("1 - Cadastrar Livro");
        System.out.println("2 - Cadastrar Leitor");
        System.out.println("3 - Realizar Emprestimo");
        System.out.println("4 - Mostrar Emprestimo");
        System.out.println("5 - Mostrar Livros");
        System.out.println("6 - Mostrar Leitores");
        System.out.println("0 - Sair");
        int op = Integer.parseInt(sc.nextLine());
        return op;
    }
    public static void main(String[] args){
        Biblioteca biblioteca = new Biblioteca("Pagode Russo");
        int opcao = 0;
        do {
            opcao = menu();
            switch (opcao){
                case 1:
                    System.out.print("Digite o nome do livro: ");
                    String nomeLivro = sc.nextLine();

                    System.out.print("Digite o nome do autor: ");
                    String nomeAutor = sc.nextLine();

                    System.out.print("Digite o email do autor: ");
                    String emailAutor = sc.nextLine();

                    System.out.print("Digite a biografia do autor: ");
                    String biografia = sc.nextLine();

                    System.out.print("Digite o ano de publicação do livro: ");
                    int anoPublicacao = Integer.parseInt(sc.nextLine());

                    biblioteca.adicionarLivro(new Livro(nomeLivro, new Autor(nomeAutor, emailAutor, biografia), anoPublicacao) );

                    break;
                case 2:
                    System.out.print("Digite o nome do leitor: ");
                    String nomeLeitor = sc.nextLine();

                    System.out.print("Digite o email do leitor: ");
                    String emailLeitor = sc.nextLine();

                    System.out.print("Digite o código de matricula do leitor: ");
                    String matriculaLeitor = sc.nextLine();

                    biblioteca.adicionarLeitores(new Leitor(nomeLeitor,emailLeitor,matriculaLeitor));
                    break;
                case 3:
                    System.out.print("Digite o nome do livro para efetuar o empréstimo: ");
                    String nomeLivroEmprestimo = sc.nextLine();

                    Livro livroEmprestimo = biblioteca.acharLivro(nomeLivroEmprestimo);
                    if(livroEmprestimo == null){
                        System.out.print("Livro não encontrado");
                        break;
                    }

                    System.out.print("Digite o número de matricula do leitor para efetuar o empréstimo: ");
                    String matriculaEmprestimo = sc.nextLine();

                    Leitor leitorEmprestimo = biblioteca.acharLeitor(matriculaEmprestimo);

                    if(leitorEmprestimo == null){
                        System.out.print("Leitor não encontrado");
                        break;
                    }

                    biblioteca.adicionarEmprestimo(new Emprestimo(livroEmprestimo, leitorEmprestimo) );
                    System.out.println("Empréstimo realizado com sucesso !\n\n");
                    break;

                case 4:
                    System.out.println(biblioteca.getEmprestimos());
                    break;
                case 5:
                    System.out.println(biblioteca.getLivros());
                    break;
                case 6:
                    System.out.println(biblioteca.getLeitores());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção invalida");
                    continue;
            }

        } while(opcao != 0);
    }
}