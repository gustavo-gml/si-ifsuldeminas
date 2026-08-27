using System;
/*Crie uma estrutura para armazenar os dados de cada eletrodoméstico: nome,
potencia (real, em kW) e tempo médio ativo por dia (real, em horas)
b. Permite listar em tela e salvar em um arquivo.
c. Permita buscar pelo seu nome.
d. Permita buscar pelos eletrodomésticos que gastam mais que um valor X.
e. Calcule e mostre o consumo diário e mensal da casa em kW e R$. Para mostrar
em R$ receba o valor do kW/h.
f. Organize tudo em uma aplicação que exiba um menu as opções de preencher
as estruturas e todas as opções das questões passadas mais a possibilidade do
usuário carregar os dados já gravados.*/
namespace CadastroEletro
{
    class Program
    {

        static void addEletro(List<Eletro> lista)
        {
            Eletro novoELetro = new Eletro();

            Console.WriteLine("Entre com o nome do eletrodoméstico:");
            novoELetro.nome = Console.ReadLine();

            Console.WriteLine("Entre com a potencia do eletrodoméstico em kwh:");
            novoELetro.potencia = float.Parse(Console.ReadLine());

            Console.WriteLine("Entre com o tempo médio de uso do eletrodoméstico:");
            novoELetro.tempoMedioUso = float.Parse(Console.ReadLine());

            lista.Add(novoELetro);
        }

        static void salvarDados(List<Eletro> listaEletros, string nomeArquivo)
        {

            using (StreamWriter writer = new StreamWriter(nomeArquivo))
            {
                foreach (Eletro e in listaEletros)
                {
                    writer.WriteLine($"{e.nome},{e.potencia},{e.tempoMedioUso}");
                }
            }
            Console.WriteLine("Dados salvos com sucesso!");


        }

        static void carregarDados(List<Eletro> listaEletros, string nomeArquivo)
        {
            if (File.Exists(nomeArquivo))
            {
                string[] linhas = File.ReadAllLines(nomeArquivo);
                foreach (string linha in linhas)
                {
                    string[] campos = linha.Split(',');
                    Eletro novoEletro = new Eletro();

                    novoEletro.nome = campos[0];
                    novoEletro.potencia = float.Parse(campos[1]);
                    novoEletro.tempoMedioUso = float.Parse(campos[2]);


                    listaEletros.Add(novoEletro);
                }
                Console.WriteLine("Dados carregados com sucesso!");
            }
            else
                Console.WriteLine("Arquivo não encontrado :(");

        }

        static void mostrarEletros(List<Eletro> listaEletro)
        {
            int i = 0;
            foreach (Eletro x in listaEletro)
            {
                i++;
                Console.WriteLine($"\n**Eletrodoméstico {i}**");
                Console.WriteLine($"{x.nome} - {x.potencia} - {x.tempoMedioUso}\n");
            }
        }

        static int menu()
        {
            int op;

            Console.WriteLine("*** Sistema de Eletrodomesticos ***");
            Console.WriteLine("1 - Adicionar Eletrodomestico");
            Console.WriteLine("2 - Mostrar Eletrodomesticos");
            Console.WriteLine("3 - Buscar Eletrodomestico");
            Console.WriteLine("4 - Atualizar Eletrodomestico");
            Console.WriteLine("5 - Excluir Eletrodomestico");
            Console.WriteLine("6 - Buscar por valor");
            Console.WriteLine("7 - Consumo mensal");
            Console.WriteLine("0 - Encerrar Programa");

            op = int.Parse(Console.ReadLine());

            return op;

        }

        static bool buscarEletro(List<Eletro> listaEletros, string nomeBusca)
        {

            foreach (Eletro x in listaEletros)
            {
                if (x.nome.ToUpper().Equals(nomeBusca.ToUpper()))
                {
                    Console.WriteLine($"\nNome: {x.nome}");
                    Console.WriteLine($"Potencia(w): {x.potencia}");
                    Console.WriteLine($"Tempo médio de uso(hs): {x.tempoMedioUso}\n");

                    return true;
                }
            }

            return false;
        }

        static bool atualizarBanda(List<Eletro> listaEletros, string nomeEletro)
        {
            int indice = buscarIndiceEletro(listaEletros, nomeEletro);

            if (indice != -1)
            {
                Console.WriteLine("*** Dados do Eletrodoméstico ***");
                Console.WriteLine($"{listaEletros[indice].nome} - {listaEletros[indice].potencia} - {listaEletros[indice].tempoMedioUso}");
                Console.WriteLine("Novos dados");

                Console.Write("Nome: ");
                listaEletros[indice].nome = Console.ReadLine();

                Console.Write("Potência: ");
                listaEletros[indice].potencia = float.Parse(Console.ReadLine());

                Console.Write("Tempo médio de uso: ");
                listaEletros[indice].tempoMedioUso = float.Parse(Console.ReadLine());

                return true;
            }
            else
                return false;
        }
        static bool removerEletro(List<Eletro> listaEletros, string nomeEletro)
        {
            int indice = buscarIndiceEletro(listaEletros, nomeEletro);

            if (indice == -1)
            {
                return false;
            }
            Console.WriteLine($"Tem certeza que deseja remover o eletrodoméstico {listaEletros[indice].nome} ?\n1 - sim  ||  2 - não");

            int resposta = int.Parse(Console.ReadLine());

            if (resposta == 1)
            {   
                listaEletros.RemoveAt(indice);
                return true;
            }
                
            return false;
            /*RemoveAt(i)*/
        }

        static int buscarIndiceEletro(List<Eletro> listaEletros, string nomeBusca)
        {
            for (int i = 0; i < listaEletros.Count; i++)
            {
                if (listaEletros[i].nome.ToUpper().Equals(nomeBusca.ToUpper()))
                {
                    return i;
                }

            }
            return -1;
        }

        static bool buscarEletroPow(List<Eletro> listaEletros, float potencia)
        {
            bool encontrou = false;
            foreach (Eletro x in listaEletros)
            {

                if (x.potencia > potencia)
                {
                    Console.WriteLine($"\nNome: {x.nome}");
                    Console.WriteLine($"Potencia(w): {x.potencia}");
                    Console.WriteLine($"Tempo médio de uso(hs): {x.tempoMedioUso}\n");

                    encontrou = true;
                }
            }

            return encontrou;
        }
        
        static void calcularConsumo(List<Eletro> listaEletros)
    {
        if (listaEletros.Count == 0)
        {
            Console.WriteLine("\nA lista de eletrodomésticos está vazia.");
            Console.WriteLine("Adicione itens (opção 1) antes de calcular o consumo.\n");
            return; 
        }

        
        Console.Write("Digite o valor do kW/h (ex: 0,75): R$ ");
        float precoKWH = float.Parse(Console.ReadLine());
        

        float consumoTotalDiarioKWH = 0;
        foreach (Eletro x in listaEletros)
        {
            float consumoDiarioItem = x.potencia * x.tempoMedioUso;
            consumoTotalDiarioKWH += consumoDiarioItem;
        }

        
        float custoTotalDiarioRS = consumoTotalDiarioKWH * precoKWH;
        float consumoTotalMensalKWH = consumoTotalDiarioKWH * 30;
        float custoTotalMensalRS = consumoTotalMensalKWH * precoKWH;

        
        Console.WriteLine("\n--- Relatório de Consumo da Casa ---");
        
        Console.WriteLine($"Consumo Diário Total: {consumoTotalDiarioKWH:N2} kWh");

        Console.WriteLine($"Custo Diário Total:   {custoTotalDiarioRS:C2}"); 
        
        Console.WriteLine("--------------------------------------");
        
        Console.WriteLine($"Consumo Mensal (30 dias): {consumoTotalMensalKWH:N2} kWh");
        Console.WriteLine($"Custo Mensal (30 dias):   {custoTotalMensalRS:C2}");
        Console.WriteLine("--------------------------------------\n");
    }
        static void Main()
        {
            List<Eletro> listaEletros = new List<Eletro>();
            carregarDados(listaEletros, "eletros.txt");

            while (true)
            {
                int op = menu();
                bool sairDoLoop = false;

                switch (op)
                {
                    case 1:
                        addEletro(listaEletros);
                        break;

                    case 2:
                        mostrarEletros(listaEletros);
                        break;

                    case 3:
                        Console.WriteLine("Qual o nome do eletrodoméstico que você deseja pesquisar ?");
                        string busca = Console.ReadLine();
                        bool achou = buscarEletro(listaEletros, busca);

                        if (!achou)
                        {
                            Console.WriteLine("Eletrodoméstico não encontrado...");
                        }
                        break;

                    case 4:
                        Console.WriteLine("Qual o nome do eletrodoméstico que você deseja atualizar ?");
                        string buscaIndice = Console.ReadLine();
                        bool acharIndice = atualizarBanda(listaEletros, buscaIndice);

                        if (!acharIndice)
                        {
                            Console.WriteLine("Eletrodoméstico não encontrado...");
                        }
                        else
                            Console.WriteLine("Eletrodoméstico atualizado com sucesso !");

                        break;

                    case 5:
                        Console.WriteLine("Qual o nome do eletrodoméstico que você deseja excluir ?");
                        string buscar = Console.ReadLine();

                        bool excluir = removerEletro(listaEletros, buscar);

                        if (!excluir)
                        {
                            Console.WriteLine("Eletrodoméstico não encontrado...");
                        }
                        else
                            Console.WriteLine("Eletrodoméstico excluido com sucesso !");
                        break;

                    case 6:
                        Console.WriteLine("Qual a potencia dos eletrodomésticos que você deseja pesquisar ?");
                        float buscaPow = float.Parse(Console.ReadLine());
                        bool achouPow = buscarEletroPow(listaEletros, buscaPow);

                        if (!achouPow)
                        {
                            Console.WriteLine("Eletrodoméstico não encontrado...");
                        }
                        break;

                    case 7:
                        calcularConsumo(listaEletros);
                        break;

                    case 0:
                        salvarDados(listaEletros, "eletros.txt");
                        Console.WriteLine("Até mais !");
                        sairDoLoop = true;
                        break;
                    default:
                        Console.WriteLine("Operação inválida !");
                        break;
                }

                if (sairDoLoop)
                    break;
                    
                Console.WriteLine("\nPressione qualquer tecla para continuar...");
                Console.ReadKey();
                Console.Clear();
                

            }
        }

        
    }
    
    
    
    
}