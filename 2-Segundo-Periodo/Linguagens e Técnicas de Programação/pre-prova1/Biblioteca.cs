using System;
using System.Linq.Expressions;

namespace MinhaBiblioteca
{
    class Biblioteca
    {
        // Lê uma matriz de um arquivo texto separado por vírgula
        public static void carregarArquivo(int[,] matriz)
        {
            string[] dados;
            try
            {
                dados = System.IO.File.ReadAllLines("dados.txt");
            }
            catch (Exception ex)
            {
                Console.WriteLine("Erro ao ler o arquivo: " + ex.Message);
                return;
            }
            int numLinhas = matriz.GetLength(0);
            int numCols = matriz.GetLength(1);
            for (int i = 0; i < numLinhas && i < dados.Length; i++)
            {
                string[] linha = dados[i].Split(',');
                for (int j = 0; j < numCols && j < linha.Length; j++)
                {
                    matriz[i, j] = int.Parse(linha[j]);
                }
            }
        }

        public static void lerMatriz(int[,] matriz)
    {
        int linhas = matriz.GetLength(0);
        int cols = matriz.GetLength(1);
        Console.WriteLine("Entre com os dados da matriz:");
        for (int i = 0; i < linhas; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                Console.Write($"[{i},{j}]:");
                matriz[i, j] = int.Parse(Console.ReadLine());
            }// fim for j
        }// fim for i
    }
        public static void gerarMatriz(int[,] matriz)
    {
        int linhas = matriz.GetLength(0);
        int cols = matriz.GetLength(1);
        Random rand = new Random();  // criando o gerador de aleatorios
        Console.WriteLine("Gerando matriz...");
        for (int i = 0; i < linhas; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                    matriz[i, j] = rand.Next(100);
            }// fim for j
        }// fim for i
    }

        public static void mostrarMatriz(int[,] matriz)
    {
        int linhas = matriz.GetLength(0);
        int cols = matriz.GetLength(1);
        Console.WriteLine("*** Matriz ***");
        for (int i = 0; i < linhas; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                Console.Write($"{matriz[i, j],2} | ");
            }// fim for j
            Console.WriteLine();
        }// fim for i     
    }
    }
}
