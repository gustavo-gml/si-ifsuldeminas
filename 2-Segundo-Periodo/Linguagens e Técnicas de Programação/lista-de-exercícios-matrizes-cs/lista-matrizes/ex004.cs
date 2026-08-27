/*Escrever um programa  que gere uma matriz de N Linhas x N Colunas  e apresente sua diagonal principal. 
Dica, limite sua matriz a uma ordem 100. */
using System;
using MinhaBiblioteca;

class ex004
{
    static void DiagPrincipal(int[,] mat)
    {
        Console.WriteLine("Diagonal principal da matriz: ");
        
        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (j == i)
                {
                    Console.Write($"[{mat[i, j]}] ");
                }
            }
        }
    }
    static void Main()
    {
        int ordem;

        Console.WriteLine("Digite a ordem de sua matriz (Tamanho máximo: 100):");
        ordem = int.Parse(Console.ReadLine());

        while (ordem > 100)
        {
            Console.WriteLine("Tamanho máximo excedido! Digite um número <= 100:");
            ordem = int.Parse(Console.ReadLine());
        }


        int[,] matriz = new int[ordem, ordem];

        Func.GerarMatInt(matriz);
        Func.MostrarMatInt(matriz);

        DiagPrincipal(matriz);




    }
}