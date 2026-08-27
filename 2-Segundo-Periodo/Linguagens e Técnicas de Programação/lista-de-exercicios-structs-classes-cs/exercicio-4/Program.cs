using System;

namespace CadastroJogos
{
    
    class Program
    {
        static void DevolverJogo(List<Jogo> listaJogos)
        {
            Console.WriteLine("Digite o nome da pessoa que efetuará a devolução");
            string dev = Console.ReadLine();

            bool encontrou = AcharNomeJogo(listaJogos, dev);
            if (!encontrou)
            {
                Console.WriteLine("Pessoa não encontrada...");
                return;
            }

            Console.WriteLine("Digite o nome do jogo: ");

            string busca = Console.ReadLine();

            bool status = BuscarNomeJogo(listaJogos, busca);

            if (!status)
            {
                Console.WriteLine("Livro não encontrado");
                return;
            }

            foreach(Jogo j in listaJogos)
            {
                if(j.emprestimo.nomePessoa.ToUpper().Equals(dev.ToUpper()) && j.titulo.ToUpper().Equals(busca.ToUpper()))
                {
                    j.emprestimo.nomePessoa = "Nulo";
                    j.emprestimo.data = "Nulo";
                    j.emprestimo.emprestado = 'N';
                }
            }
            
        }

         static bool AcharNomeJogo(List<Jogo> listaJogos, string stringBusca)
        {

            bool encontrou = false;

            foreach(Jogo j in listaJogos)
            {
                if (j.emprestimo.nomePessoa.ToUpper().Equals(stringBusca.ToUpper()))
                {
                    encontrou = true;
                }
            }

            return encontrou;
        }
        
        static void EmprestarJogo(List<Jogo> listaJogo)
        {
            Console.WriteLine("Digite o nome do jogo que será emprestado");
            string busca = Console.ReadLine();

            bool status = BuscarNomeJogo(listaJogo, busca);

            if (!status)
            {
                Console.WriteLine("Livro não encontrado");
                return;
            }

            Console.WriteLine("Digite o nome da pessoa que requisitou o empréstimo: ");
            string nome = Console.ReadLine();

            Console.WriteLine("Digite a data de realização do empréstimo (Ex: 22/10/25 )");
            string data = Console.ReadLine();

            foreach(Jogo j in listaJogo)
            {
                if (j.titulo.ToUpper().Equals(busca.ToUpper()))
                {
                    j.emprestimo.data = data;
                    j.emprestimo.nomePessoa = nome;
                    j.emprestimo.emprestado = 'S';
                    return;
                }
            }
        }
        static bool BuscarNomeJogo(List<Jogo> listaJogos, string stringBusca)
        {
            int i=0;

            bool encontrou = false;

            foreach(Jogo j in listaJogos)
            {
                if (j.titulo.ToUpper().Equals(stringBusca.ToUpper()))
                {
                    encontrou = true;
                    i++;

                    Console.WriteLine("-----------------------------");
                    Console.WriteLine($"Jogo {i}\n");
                    Console.WriteLine($"{j.titulo} - {j.console} - {j.ano} - {j.ranking}");
                    Console.WriteLine("-----------------------------");
                }
            }

            return encontrou;
        }

         static bool BuscarConsoleJogo(List<Jogo> listaJogos, string stringBusca)
        {
            int i=0;

            bool encontrou = false;

            foreach(Jogo j in listaJogos)
            {
                if (j.console.ToUpper().Equals(stringBusca.ToUpper()))
                {
                    encontrou = true;
                    i++;

                    Console.WriteLine("-----------------------------");
                    Console.WriteLine($"Jogo {i}\n");
                    Console.WriteLine($"{j.titulo} - {j.console} - {j.ano} - {j.ranking}");
                    Console.WriteLine("-----------------------------");
                }
            }

            return encontrou;
        }
        static void AddJogo(List<Jogo> listaJogos)
        {
            Jogo novoJogo = new Jogo();
            Data novo = new Data();

            novo.data = "Nulo";
            novo.nomePessoa = "Nulo";
            novo.emprestado= 'N';

            Console.WriteLine("\n---------------------------------");
            Console.Write("Digite o nome do jogo: ");
            novoJogo.titulo = Console.ReadLine();

            Console.Write("Digite a plataforma do jogo: ");
            novoJogo.console = Console.ReadLine();

            Console.Write("Digite o ano de publicação do jogo: ");
            novoJogo.ano = int.Parse(Console.ReadLine());

            Console.Write("Digite o ranking do jogo: ");
            novoJogo.ranking = int.Parse(Console.ReadLine());

            novoJogo.emprestimo = novo;

            listaJogos.Add(novoJogo);
            Console.WriteLine("---------------------------------\n");
        }

        static void MostarJogosCadastrados(List<Jogo> listaJogos)
        {
            int i = 0;

            foreach(Jogo j in listaJogos)
            {
                i++;

                Console.WriteLine("-----------------------------");
                Console.WriteLine($"Jogo {i}\n");
                Console.WriteLine($"{j.titulo} - {j.console} - {j.ano} - {j.ranking} - {j.emprestimo.data} - {j.emprestimo.nomePessoa} - {j.emprestimo.emprestado}");
                Console.WriteLine("-----------------------------");
            }
        }

         static void SalvarDados(List<Jogo> listaJogos, string nomeArquivo)
        {

            using (StreamWriter writer = new StreamWriter(nomeArquivo))
            {
                foreach (Jogo j in listaJogos)
                {
                    writer.WriteLine($"{j.titulo},{j.console},{j.ano},{j.ranking},{j.emprestimo.data},{j.emprestimo.nomePessoa},{j.emprestimo.emprestado}");
                }
            }
            Console.WriteLine("Dados salvos com sucesso!");

        }

        static void CarregarDados(List<Jogo> listaJogos, string nomeArquivo)
        {
            if (File.Exists(nomeArquivo))
            {
                string[] linhas = File.ReadAllLines(nomeArquivo);
                foreach (string linha in linhas)
                {
                    string[] campos = linha.Split(',');
                    Jogo novoJogo = new Jogo();
                    novoJogo.emprestimo = new Data();

                    novoJogo.titulo = campos[0];
                    novoJogo.console = campos[1];
                    novoJogo.ano = int.Parse(campos[2]);
                    novoJogo.ranking= int.Parse(campos[3]);
                    novoJogo.emprestimo.data= campos[4];
                    novoJogo.emprestimo.nomePessoa= campos[5];
                    novoJogo.emprestimo.emprestado= char.Parse(campos[6]);


                    listaJogos.Add(novoJogo);
                }
                Console.WriteLine("Dados carregados com sucesso!");
            }
            else
                Console.WriteLine("Arquivo não encontrado :(");

        }
        static int Menu()
    {
        Console.WriteLine("***Sistema LoucaDoura *** ");

        Console.WriteLine("1 - Cadastrar Jogo");
        Console.WriteLine("2 - Mostar Jogos Cadastrados");
        Console.WriteLine("3 - Buscar Jogos por Título ou Plataforma");
        Console.WriteLine("4 - Realizar empréstimo");
        Console.WriteLine("5 - Devolver empréstimo");
        Console.WriteLine("0 - Sair");
        
        int op = int.Parse(Console.ReadLine());

        return op;

    }
        static void Main()
        {
            List<Jogo> listaJogos = new List<Jogo>();
            bool sair = false;

            CarregarDados(listaJogos, "jogos.txt");
            while (true)
            {
                int op = Menu();

                switch (op)
                {
                    case 1:
                    AddJogo(listaJogos);
                    break;

                    case 2:
                    MostarJogosCadastrados(listaJogos);
                    break;

                    case 3:
                        Console.Clear();
                        Console.WriteLine("\n-------------------------");
                        Console.WriteLine("1 - Buscar por Título");
                        Console.WriteLine("2 - Buscar por Plataforma");
                    
                        int escolha = int.Parse(Console.ReadLine());
                        bool status;
                        if(escolha == 1)
                        {
                            Console.WriteLine("Digite o nome para busca: ");
                            string stringBusca = Console.ReadLine();

                            status = BuscarNomeJogo(listaJogos, stringBusca);

                            if (!status)
                            {
                                Console.WriteLine("Nenhum jogo cadastrado com esse nome");
                            }
                        }else if(escolha == 2)
                        {
                            Console.WriteLine("Digite o nome para busca: ");
                            string stringBusca = Console.ReadLine();

                            status = BuscarConsoleJogo(listaJogos, stringBusca);

                            if (!status)
                            {
                                Console.WriteLine("Nenhum jogo cadastrado com esse nome");
                            }
                        }
                        else
                        {
                            Console.WriteLine("Opção inválida");
                        }
                        break;

                    case 4:
                    EmprestarJogo(listaJogos);
                    break;

                    case 5:
                    DevolverJogo(listaJogos);
                    break;

                    

                    case 0:
                    sair = true;
                    SalvarDados(listaJogos, "jogos.txt");
                    break;
                }

                if (sair)
                {
                    break;
                }
            }
        }
    }
}