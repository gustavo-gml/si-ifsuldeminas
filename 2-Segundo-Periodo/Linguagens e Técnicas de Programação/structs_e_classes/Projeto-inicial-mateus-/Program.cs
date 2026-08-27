using System;
namespace CadastroBandas
{
    class Program
    {
        static void AddBanda(List<Banda> listaBandas)
        {
            Banda novaBanda = new Banda(); //parenteses para objetos

            Console.WriteLine("\nDados da Banda");

            Console.Write("Nome: ");
            novaBanda.nome = Console.ReadLine();

            Console.Write("Genero: ");
            novaBanda.genero = Console.ReadLine();

            Console.Write("Número de integrantes: ");
            novaBanda.integrantes = int.Parse(Console.ReadLine());

            Console.Write("Ranking da banda: ");
            novaBanda.ranking = int.Parse(Console.ReadLine());

            listaBandas.Add(novaBanda);

            Console.WriteLine("--------");
        }

        static void mostrarBandas(List<Banda> listaBandas)
        {
            int i = 0;
            foreach (Banda x in listaBandas)
            {
                i++;
                Console.WriteLine($"\n**Banda {i}**");
                Console.WriteLine($"{x.nome} - {x.genero} - {x.integrantes} - {x.ranking}");
            }
        }

        static int menu()
        {
            int op;

            Console.WriteLine("*** Sistema de Cadastro de Bandas 4U ***");
            Console.WriteLine("1 - Adicionar Bandas");
            Console.WriteLine("2 - Mostrar Bandas");
            Console.WriteLine("3 - Buscar Banda");
            Console.WriteLine("4 - Atualizar Banda");
            Console.WriteLine("5 - Excluir Banda");
            Console.WriteLine("0 - Encerrar Programa");

            op = int.Parse(Console.ReadLine());

            return op;

        }

        static void salvarDados(List<Banda> listaBandas, string nomeArquivo)
        {

            using (StreamWriter writer = new StreamWriter(nomeArquivo))
            {
                foreach (Banda b in listaBandas)
                {
                    writer.WriteLine($"{b.nome},{b.genero},{b.integrantes},{b.ranking}");
                }
            }
            Console.WriteLine("Dados salvos com sucesso!");


        }

        static void carregarDados(List<Banda> listaBandas, string nomeArquivo)
        {
            if (File.Exists(nomeArquivo))
            {
                string[] linhas = File.ReadAllLines(nomeArquivo);
                foreach (string linha in linhas)
                {
                    string[] campos = linha.Split(',');
                    Banda novaBanda = new Banda();

                    novaBanda.nome = campos[0];
                    novaBanda.genero = campos[1];
                    novaBanda.integrantes = int.Parse(campos[2]);
                    novaBanda.ranking = int.Parse(campos[3]);

                    listaBandas.Add(novaBanda);
                }
                Console.WriteLine("Dados carregados com sucesso!");
            }
            else
                Console.WriteLine("Arquivo não encontrado :(");

        }

        static bool buscarBanda(List<Banda> listaBandas, string nomeBusca)
        {

            foreach (Banda x in listaBandas)
            {
                if (x.nome.ToUpper().Equals(nomeBusca.ToUpper()))
                {
                    Console.WriteLine($"Nome: {x.nome}");
                    Console.WriteLine($"Genero: {x.genero}");
                    Console.WriteLine($"Quantidade de integrantes: {x.integrantes}");
                    Console.WriteLine($"Rankink da banda: {x.ranking}");
                    return true;
                }
            }

            return false;
        }

        static int buscarIndiceBanda(List<Banda> listaBandas, string nomeBusca)
        {
            for (int i = 0; i < listaBandas.Count; i++)
            {
                if (listaBandas[i].nome.ToUpper().Equals(nomeBusca.ToUpper()))
                {
                    return i;
                }

            }
            return -1;
        }

        static bool atualizarBanda(List<Banda> listaBandas, string nomeBanda)
        {
            int indice = buscarIndiceBanda(listaBandas, nomeBanda);

            if (indice != -1)
            {
                Console.WriteLine("*** Dados da banda ***");
                Console.WriteLine($"{listaBandas[indice].nome} - {listaBandas[indice].genero} - {listaBandas[indice].integrantes} - {listaBandas[indice].ranking}");
                Console.WriteLine("Novos dados");

                Console.Write("Nome: ");
                listaBandas[indice].nome = Console.ReadLine();

                Console.Write("Genero: ");
                listaBandas[indice].genero = Console.ReadLine();

                Console.Write("Número de integrantes: ");
                listaBandas[indice].integrantes = int.Parse(Console.ReadLine());

                Console.Write("Ranking da banda: ");
                listaBandas[indice].ranking = int.Parse(Console.ReadLine());

                return true;
            }
            else
                return false;
        }
        
        static bool removerBanda(List<Banda> listaBandas, string nomeBanda)
        {
            int indice = buscarIndiceBanda(listaBandas, nomeBanda);

            if (indice == -1)
            {
                return false;
            }
            Console.WriteLine($"Tem certeza que deseja remover a banda {listaBandas[indice].nome} ?\n1 - sim  ||  2 - não");

            int resposta = int.Parse(Console.ReadLine());

            if (resposta == 1)
            {   
                listaBandas.RemoveAt(indice);
                return true;
            }
                
            return false;
            /*RemoveAt(i)*/
        }



        static void Main()
        {
            List<Banda> listaBandas = new List<Banda>();
            carregarDados(listaBandas, "bandas.txt");

            while(true)
            {
                int acao = menu();



                if (acao == 1)
                {
                    Console.WriteLine("Quantas bandas você vai cadastrar ?");
                    int quant = int.Parse(Console.ReadLine());

                    for (int i = 0; i < quant; i++)
                        AddBanda(listaBandas);

                }

                else if (acao == 2)
                    mostrarBandas(listaBandas);

                else if (acao == 3)
                {
                    Console.WriteLine("Qual o nome da banda ?");

                    string nomeBanda = Console.ReadLine();

                    bool achou = buscarBanda(listaBandas, nomeBanda);

                    if (!achou)
                        Console.WriteLine("Banda não encontrada...");

                }

                else if (acao == 4)
                {
                    Console.WriteLine("Qual o nome da banda ?");

                    string nomeBanda = Console.ReadLine();

                    bool achou = atualizarBanda(listaBandas, nomeBanda);

                    if (!achou)
                        Console.WriteLine("Banda não encontrada...");

                }

                else if (acao == 5){
                    Console.WriteLine("Qual o nome da banda ?");
                
                    string nomeBanda = Console.ReadLine();

                    bool achou = removerBanda(listaBandas, nomeBanda);
                    Console.WriteLine("Dados removidos com sucesso! ");

                    if (!achou)
                        Console.WriteLine("Banda não encontrada...");

                }


                else
                {
                    salvarDados(listaBandas, "bandas.txt");
                    Console.WriteLine("Até a próxima !");
                    break;
                }

                Console.WriteLine("\nPressione qualquer tecla para continuar...");
                Console.ReadKey();
                Console.Clear();
                
            }
            
        }
        
    }
}