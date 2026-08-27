using System;
using MinhaBiblioteca;


class ex011
{
    static int SomaPrincipal(int[,] mat)
    {
        int soma = 0;
        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (i == j)
                {
                    soma += mat[i, j];
                }
            }
        }
        return soma;
    }

    static int SomaSecundaria(int[,] mat)
    {
        int soma = 0;
        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (i + j == mat.GetLength(0) - 1)
                {
                    soma += mat[i, j];
                }
            }
        }
        return soma;
    }
    static void Main()
    {
        int n;

        Console.Write("Digite a ordem da matriz: ");
        n = int.Parse(Console.ReadLine());

        int[,] tesouro = new int[n, n];

        Func.GerarMatInt(tesouro);

        Console.WriteLine("Mapa dp tesouro (Quantidade de moedas em cada região): ");
        Func.MostrarMatInt(tesouro);
        

        int principal = SomaPrincipal(tesouro);
        int secundaria = SomaSecundaria(tesouro);

        Console.WriteLine($"Soma da Diagonal Principal: {principal} ");
        Console.WriteLine($"Soma da Diagonal Principal: {secundaria} \n");

        if (principal > secundaria)
        {
            Console.WriteLine("O maior tesouro está na diagonal principal, vamos para lá!");
        }
        else
        {
            Console.WriteLine("O maior tesouro está na diagonal secundaria, vamos para lá!");
        }


    }
}