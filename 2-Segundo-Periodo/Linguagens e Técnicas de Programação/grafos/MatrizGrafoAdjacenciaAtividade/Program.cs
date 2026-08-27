using System;
namespace MatrizGrafo
{   

    
    class Program
    {

        

        static int menu()
        {
            
            Console.WriteLine("1 - Mostrar matriz de adjacência (R)");
            Console.WriteLine("2 - Verificar propriedades do grafo");
            Console.WriteLine("3 - Calcular caminhos de comprimento 2 (R2)");
            Console.WriteLine("4 - Verificar transitividade");
            Console.WriteLine("5 - Calcular R infinito (fecho transitivo)");
            Console.WriteLine("6 - Calcular matriz de conexividade");
            Console.WriteLine("7 - Desenhar gráfico ASCII");
            Console.WriteLine("0-  Sair");
            Console.WriteLine("Digite a opção desejada: ");
            

            return int.Parse(Console.ReadLine());
        }
        static void imprimirMatriz(int[,] matriz)
        {
            int tamanho = matriz.GetLength(0);
            for (int i = 0; i < tamanho; i++)
            {
                for (int j = 0; j < tamanho; j++)
                {
                    System.Console.Write(matriz[i, j] + " ");
                }
                System.Console.WriteLine();
            }
        }
        static void Main(string[] args)
        {
            Grafo meuGrafo = new Grafo(10);
            meuGrafo.carregarMatrizDeArquivo("matrizSimetrica.txt");

            
            


            int opcao;

            do
            {
                opcao = menu();
                Console.Clear();
                switch (opcao)
                {
                    case 1:
                        meuGrafo.mostrarMatriz();
                        break;
                    case 2:
                        meuGrafo.mostrarFalhaReflexiva();
                        meuGrafo.mostrarFalhaSimetrica();
                        break;

                    case 3:
                        meuGrafo.mostrarCaminho2();
                        break;
                    case 4:
                         meuGrafo.mostrarFalhaTransitividade();
                        break;
                    case 5:
                        meuGrafo.mostrarRInfinito();
                        break;
                    case 6:
                        meuGrafo.mostrarMatrizConexividade();
                        break;
                    case 7:
                        meuGrafo.desenharGrafoASCII();
                        break;
                    case 0:
                        Console.WriteLine("Saindo...");
                        break;
                    default:
                        Console.WriteLine("Opção inválida. Tente novamente.");
                        break;
                }
                Console.ReadLine();
                Console.Clear();
            }while (opcao != 0);
        }
    }
}
