using System;
using MinhaBiblioteca;
/*Crie uma função que receba uma matriz N por M e conte quantos números iguais a X existem na matriz. X 
deve ser informado pelo usuário (parâmetro da função). Retorne a quantas ocorrências de X existem na 
matriz. */
class ex003
{
    static int ContarX(int[,] mat, int X)
    {
        int count = 0;

        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (X == mat[i, j])
                {
                    count++;
                }
            }
        }
        return count;
    }
    static void Main()
    {
        int linhas = Func.LinMatriz();
        int colunas = Func.ColMatriz();
        Console.WriteLine();

        int[,] matIgual = new int[linhas, colunas];

        Func.LerMatInt(matIgual);

        int X, count;

        Console.WriteLine("Digite um número para a contagem de ocorrências: ");
        X = int.Parse(Console.ReadLine());

        count = ContarX(matIgual, X);

        Console.WriteLine($"O número {X} apareceu {count} vezes na matriz !");
    }
}
