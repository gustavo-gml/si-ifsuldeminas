using System;
using MinhaBiblioteca;

class ex002
{
    static int MaiorValor(int[,] mat)
    {
        int maior = mat[0, 0];

        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (mat[i, j] > maior)
                {
                    maior = mat[i, j];
                }
            }
        }
        return maior;
    }
    static void Main()
    {

        int linhas = Func.LinMatriz();
        int colunas = Func.ColMatriz();

        int[,] matriz = new int[linhas, colunas];

        Func.LerMatInt(matriz);

        Console.WriteLine($"O maior valor da matriz digitada foi: {MaiorValor(matriz)}");
    }
}