using System;
using MinhaBiblioteca;

class ex006()
{
    static void SomaMat(int[,] mat1, int[,] mat2, int[,] soma)
    {
        Console.WriteLine("A soma das matrizes é: ");
        for (int i = 0; i < mat1.GetLength(0); i++)
        {
            for (int j = 0; j < mat1.GetLength(1); j++)
            {
                soma[i, j] = mat1[i, j] + mat2[i, j];
                Console.Write($"[{soma[i, j]}] ");
            }
            Console.WriteLine();
        }
    }
    static void Main()
    {
        Console.WriteLine("Informe a quantidade de linhas da primeira matriz: ");
        int linhas1 = int.Parse(Console.ReadLine());
        Console.WriteLine("Informe a quantidade de colunas da primeira matriz: ");
        int colunas1 = int.Parse(Console.ReadLine());

        Console.WriteLine("Informe a quantidade de linhas da segunda matriz: ");
        int linhas2 = int.Parse(Console.ReadLine());
        Console.WriteLine("Informe a quantidade de colunas da segunda matriz: ");
        int colunas2 = int.Parse(Console.ReadLine());

        if (linhas1 != linhas2 || colunas1 != colunas2)
        {
            Console.WriteLine("As matrizes precisam ser de mesma ordem !");
        }
        else
        {
            int[,] mat1 = new int[linhas1, colunas1];
            int[,] mat2 = new int[linhas1, colunas1];
            int[,] soma = new int[linhas1, colunas1];

            Console.WriteLine("Matriz 1");
            Func.LerMatInt(mat1);

            Console.WriteLine("Matriz 2");
            Func.LerMatInt(mat2);

            SomaMat(mat1, mat2, soma);

        }
    }
}