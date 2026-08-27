/*Teste a criação e busca de documentos. Explique como equals/hashCode  é utilizado no contexto da aula. Cite pelo menos dois CPFs usados nos testes (por exemplo, "123.456.789-00" e "987.654.321-00") e a saída. */
import java.time.LocalDate;
import java.util.*;

public class App {

    public static HashMap<Documento, Pessoa> gerarPessoas() {
        HashMap<Documento, Pessoa> mapa = new HashMap<>();
        mapa.put(new Documento("123.456.789-00"), new Pessoa("Ana", new Documento("123.456.789-00"), LocalDate.parse("1990-01-01")));
        mapa.put(new Documento("456.789.123-00"), new Pessoa("Bruno", new Documento("456.789.123-00"), LocalDate.parse("1991-01-01")));
        mapa.put(new Documento("789.123.456-00"), new Pessoa("Carla", new Documento("789.123.456-00"), LocalDate.parse("1992-01-01")));
        mapa.put(new Documento("123.789.456-00"), new Pessoa("Daniel", new Documento("123.789.456-00"), LocalDate.parse("1993-01-01")));
        mapa.put(new Documento("456.123.789-00"), new Pessoa("Elisa", new Documento("456.123.789-00"), LocalDate.parse("1994-01-01")));
        mapa.put(new Documento("789.456.123-00"), new Pessoa("Fabio", new Documento("789.456.123-00"), LocalDate.parse("1995-01-01")));
        mapa.put(new Documento("987.654.321-00"), new Pessoa("Gabriela", new Documento("987.654.321-00"), LocalDate.parse("1996-01-01")));
        mapa.put(new Documento("654.321.987-00"), new Pessoa("Henrique", new Documento("654.321.987-00"), LocalDate.parse("1997-01-01")));
        mapa.put(new Documento("321.987.654-00"), new Pessoa("Isabela", new Documento("321.987.654-00"), LocalDate.parse("1998-01-01")));
        mapa.put(new Documento("147.258.369-00"), new Pessoa("Jorge", new Documento("147.258.369-00"), LocalDate.parse("1999-01-01")));
        return mapa;
    }

    static int menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------HASH MAP JAVA--------------------");
        System.out.println("1 - Listar pessoas");
        System.out.println("2 - Buscar pessoa por CPF");
        System.out.println("3 - Adicionar pessoa");
        System.out.println("4 - Remover pessoa por CPF");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
        return sc.nextInt();
    }

    public static void main(String[] args) throws Exception {
        HashMap<Documento, Pessoa> hashMapPessoas = gerarPessoas();
        Scanner leia = new Scanner(System.in);
        int op;

        do {
            op = menu();
            switch (op) {
                case 1:
                    System.out.println("\nListando pessoas:");
                    for (Map.Entry<Documento, Pessoa> entry : hashMapPessoas.entrySet()) {
                        System.out.println("CPF: " + entry.getKey() + " - Pessoa: " + entry.getValue());
                    }
                    break;
                case 2:
                    System.out.println("\nRecuperando pessoa pelo CPF:");
                    Documento docBuscaDocumento = new Documento(leia.nextLine());
                    Pessoa pessoa = hashMapPessoas.get(docBuscaDocumento);
                    if (pessoa != null) {
                        System.out.println("Pessoa encontrada: " + pessoa);
                    } else {
                        System.out.println("Pessoa não encontrada para o CPF: " + docBuscaDocumento);
                    }
                    break;
                case 3:
                    System.out.println("\nAdicionando nova pessoa:");
                    System.out.print("Digite o nome: ");
                    String nome = leia.nextLine();
                    System.out.print("Digite o CPF: ");
                    Documento novoDocumento = new Documento(leia.nextLine());
                    System.out.print("Digite a data de nascimento (YYYY-MM-DD): ");
                    LocalDate dataNascimento = LocalDate.parse(leia.nextLine());
                    Pessoa novaPessoa = new Pessoa(nome, novoDocumento, dataNascimento);
                    hashMapPessoas.put(novoDocumento, novaPessoa);
                    System.out.println("Pessoa adicionada com sucesso!");
                    break;
                case 4:
                    System.out.println("\nRemovendo pessoa pelo CPF:");
                    System.out.print("Digite o CPF: ");
                    Documento docRemover = new Documento(leia.nextLine());
                    if (hashMapPessoas.remove(docRemover) != null) {
                        System.out.println("Pessoa removida com sucesso!");
                    } else {
                        System.out.println("Pessoa não encontrada para o CPF: " + docRemover);
                    }
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;

            }
        } while (op != 0);
    }
}
