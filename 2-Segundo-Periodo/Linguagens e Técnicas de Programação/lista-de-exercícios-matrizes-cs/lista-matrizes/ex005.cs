using System;
using MinhaBiblioteca;
/*Escrever um programa  que gere uma matriz de N Linhas x N Colunas  e apresente sua diagonal secundária*/
class ex005
{
    static void DiagSecundaria(int[,] mat)
    {
        int ordem = mat.GetLength(0);
        int decremento = 1;
        Console.WriteLine("Diagonal secundária da matriz: ");

        for (int i = 0; i < ordem; i++)
        {
            
            for (int j = 0; j < ordem; j++)
            {
                int controle = ordem;
                if (i == j - (controle - decremento))
                {
                    Console.Write($"[{mat[i, j]}] ");
                }
            }
            decremento+=2;
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

        DiagSecundaria(matriz);
    }
}