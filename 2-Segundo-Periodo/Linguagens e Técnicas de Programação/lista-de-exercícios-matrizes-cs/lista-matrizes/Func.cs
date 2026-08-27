using System;

namespace MinhaBiblioteca
{
    class Func
    {
        public static int LinMatriz() /*Solicita ao usuário a quantidade de linhas de uma matriz qualquer*/
        {
            int linhas;

            Console.WriteLine("Digite a quantidade de linhas da matriz:");
            linhas = int.Parse(Console.ReadLine());
            return linhas; 
        } 
        public static int ColMatriz() /*Solicita ao usuário a quantidade de colunas de uma matriz qualquer*/
        {
            int colunas;

            Console.WriteLine("Digite a quantidade de colunas da matriz:");
            colunas = int.Parse(Console.ReadLine());

            return colunas;
        }
        public static void LerMatInt(int[,] mat) /*Solita que o usuário preencha uma matriz com dados do tipo int*/
        {
            Console.WriteLine("Entre com os dados da matriz.");
            for (int i = 0; i < mat.GetLength(0); i++)
            {
                for (int j = 0; j < mat.GetLength(1); j++)
                {
                    Console.Write($"Posição [{i},{j}]: ");
                    mat[i, j] = int.Parse(Console.ReadLine());
                }

            }
            Console.WriteLine();
        }
        
        public static void LerMatFloat(float[,] mat) /*Solita que o usuário preencha uma matriz com dados do tipo int*/
        {
            Console.WriteLine("Entre com os dados da matriz.");
            for (int i = 0; i < mat.GetLength(0); i++)
            {
                for (int j = 0; j < mat.GetLength(1); j++)
                {
                    Console.Write($"Posição [{i},{j}]: ");
                    mat[i, j] = float.Parse(Console.ReadLine());
                }

            }
            Console.WriteLine();
        }

        public static void GerarMatInt(int[,] mat) /*Preenche uma matriz do tipo int aleatoriamente*/
        {
            Console.WriteLine("Gerando matriz...");

            Random rand = new Random();

            for (int i = 0; i < mat.GetLength(0); i++)
            {
                for (int j = 0; j < mat.GetLength(1); j++)
                {
                    mat[i, j] = rand.Next(101);
                }

            }
            Console.WriteLine();
        }

        public static void MostrarMatInt(int[,] mat) /*Mostra os dados de uma matriz linha por linha*/
        {

            for (int i = 0; i < mat.GetLength(0); i++)
            {
                for (int j = 0; j < mat.GetLength(1); j++)
                {
                    Console.Write($"[{mat[i, j]}] ");
                }
                Console.WriteLine();
            }
            Console.WriteLine();
        }
        
        public static void MostrarMatFloat(float[,] mat) /*Mostra os dados de uma matriz linha por linha*/
        {

            for (int i = 0; i < mat.GetLength(0); i++)
            {
                for (int j = 0; j < mat.GetLength(1); j++)
                {
                    Console.Write($"[{mat[i, j]}] ");
                }
                Console.WriteLine();
            }
            Console.WriteLine();
        }
    }
}