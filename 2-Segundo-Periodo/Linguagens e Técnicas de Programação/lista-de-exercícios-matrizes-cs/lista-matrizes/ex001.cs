using System;
using MinhaBiblioteca;

class ex001
{
    static int MenorValor(int[,] mat)
    {
        int menor = mat[0, 0];

        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (mat[i, j] < menor)
                {
                    menor = mat[i, j];
                }
            }
        }
        return menor;
    }
    static void Main()
    {

        int linhas = Func.LinMatriz();
        int colunas = Func.ColMatriz();

        int[,] matriz = new int[linhas, colunas];

        Func.LerMatInt(matriz);

        Console.WriteLine($"O menor valor da matriz digitada foi: {MenorValor(matriz)}");
    }
}