using System;
using System.ComponentModel.Design;

namespace CadastroBiblioteca
{
    class Program
    {


        static int menu()
        {
            Console.WriteLine("***Sistema de cadastros de livro Libre Turing***");
            Console.WriteLine("1 - Adicionar Livro");
            Console.WriteLine("2 - Mostrar Livros");
            Console.WriteLine("3 - Pesquisar por titulo");
            Console.WriteLine("4 - Pesquisa por ano");
            Console.WriteLine("0 - Encerrar Sessão");
            int op = int.Parse(Console.ReadLine());

            return op;
        }

        static void salvarDados(List<Livro> listaLivros, string nomeArquivo)
        {

            using (StreamWriter writer = new StreamWriter(nomeArquivo))
            {
                foreach (Livro l in listaLivros)
                {
                    writer.WriteLine($"{l.titulo},{l.autor},{l.ano},{l.prateleira}");
                }
            }
            Console.WriteLine("Dados salvos com sucesso!");

        }

        static void carregarDados(List<Livro> listaLivro, string nomeArquivo)
        {
            if (File.Exists(nomeArquivo))
            {
                string[] linhas = File.ReadAllLines(nomeArquivo);
                foreach (string linha in linhas)
                {
                    string[] campos = linha.Split(',');
                    Livro novoLivro = new Livro();

                    novoLivro.titulo = campos[0];
                    novoLivro.autor = campos[1];
                    novoLivro.ano = int.Parse(campos[2]);
                    novoLivro.prateleira = int.Parse(campos[3]);

                    listaLivro.Add(novoLivro);
                }
                Console.WriteLine("Dados carregados com sucesso!");
            }
            else
                Console.WriteLine("Arquivo não encontrado :(");

        }

        static void addLivro(List<Livro> listaLivros)
        {
            Livro novoLivro = new Livro();

            Console.Write("Digite o título do livro: ");
            novoLivro.titulo = Console.ReadLine();

            Console.Write("Digite o nome do autor do livro: ");
            novoLivro.autor = Console.ReadLine();

            Console.Write("Digite o ano de publicação do livro: ");
            novoLivro.ano = int.Parse(Console.ReadLine());

            Console.Write("Digite o número da prateleira do livro: ");
            novoLivro.prateleira = int.Parse(Console.ReadLine());

            listaLivros.Add(novoLivro);
        }

        static void mostrarLivros(List<Livro> listaLivros)
        {
            int i = 0;
            if (listaLivros.Count() == 0)
            {
                Console.WriteLine("Nenhum livro cadastrado");
            }
            else
            {
                foreach (Livro livro in listaLivros)
                {
                    i++;
                    Console.WriteLine("-----------------------");
                    Console.WriteLine($"Livro {i}\n");
                    Console.WriteLine($"Título do livro:{livro.titulo}");
                    Console.WriteLine($"Autor do livro:{livro.autor}");
                    Console.WriteLine($"Ano de publicacao do livro:{livro.ano}");
                    Console.WriteLine($"Numero da prateleira do livro:{livro.prateleira}");
                    Console.WriteLine("-----------------------\n");
                }
            }
        }

        static bool buscarTitulo(List<Livro> listaLivros, string buscaTitulo)
        {
            bool achou = false;
            foreach (Livro l in listaLivros)
            {
                if (l.titulo.ToUpper().Equals(buscaTitulo.ToUpper()))
                {
                    achou = true;
                    Console.WriteLine("-------------------------------------------------------");

                    Console.WriteLine($"O livro '{l.titulo}' se encontra na prateleira de número {l.prateleira}");

                    Console.WriteLine("-------------------------------------------------------\n");
                }
            }

            return achou;
        }
        
        static bool buscarData(List<Livro> listaLivros, int data)
        {
            bool achou = false;
            int i = 0;
            foreach (Livro l in listaLivros)
            {
                i++;
                if (l.ano >= data )
                {
                    achou = true;
                    
                    Console.WriteLine("-----------------------");
                    Console.WriteLine($"Livro {i}\n");
                    Console.WriteLine($"Título do livro:{l.titulo}");
                    Console.WriteLine($"Autor do livro:{l.autor}");
                    Console.WriteLine($"Ano de publicacao do livro:{l.ano}");
                    Console.WriteLine($"Numero da prateleira do livro:{l.prateleira}");
                    Console.WriteLine("-----------------------\n");
                }
            }

            return achou;
        }
        static void Main()
        {
            List<Livro> listaLivros = new List<Livro>();
            carregarDados(listaLivros, "Livros.txt");
            int op;
            bool exit = false;



            while (true)
            {
                op = menu();

                switch (op)
                {
                    case 1:
                        addLivro(listaLivros);
                        break;

                    case 2:
                        mostrarLivros(listaLivros);
                        break;

                    case 3:
                        Console.Write("Digite um título para consulta: ");
                        string buscaTitulo = Console.ReadLine();

                        bool existeTitulo = buscarTitulo(listaLivros, buscaTitulo);

                        if (!existeTitulo)
                        {
                            Console.WriteLine("Livro não encontrado.");
                        }

                        break;

                    case 4:
                        Console.WriteLine("Digite um ano para constatar quais os livros cadastrados com data de lançamento igual ou superior: ");
                        int data = int.Parse(Console.ReadLine());

                        bool existeData = buscarData(listaLivros, data);

                        if (!existeData)
                        {
                            Console.WriteLine("Não existem livros cadastrados com data de lançamento igual ou superior a informada.");
                        }
                        break;

                    case 0:
                        salvarDados(listaLivros, "Livros.txt");
                        exit = true;
                        break;

                    default:
                        Console.WriteLine("Opcao invalida.");
                        break;
                }


                if (exit)
                {
                    break;
                }
                Console.Write("Digite Qualquer tecla para continuar... ");
                Console.ReadKey();
                Console.Clear();
            }
        }

    }
}
