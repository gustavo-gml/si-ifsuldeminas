/*Faça um algoritimo que calcula a multiplicação de duas matrizes A e B informadas pelo usuario*/

using System;
using MinhaBiblioteca;

class extra
{
    static float SomaMultiVetorial(float[] vet1, float[] vet2)
    {
        float result = 0;

        for (int i = 0; i < vet1.Length; i++)
        {
            result += vet1[i] * vet2[i];
        }
        return result;
    }
    static float[,] MultiplicarMatrizes(float[,] mat1, float[,] mat2)
    {
        int linhaResult = mat1.GetLength(0);
        int colunaResult = mat2.GetLength(1);

        float[,] resultado = new float[linhaResult, colunaResult];

        float[] vet1 = new float[mat1.GetLength(1)];
        float[] vet2 = new float[mat2.GetLength(0)];

        for(int i = 0; i < linhaResult; i++)
        {
            for(int k = 0; k< mat1.GetLength(1); k++)
            {
                vet1[k] = mat1[i, k];
            }
                for(int j = 0; j < colunaResult; j++)
                {
                    for(int l = 0; l < mat2.GetLength(0); l++)
                    {
                        vet2[l] = mat2[l, j];
                    }
                    resultado[i, j] = SomaMultiVetorial(vet1, vet2);
            }
        }

        return resultado;
    }
    static void Main()
    {
        int m, n, o, p;

        Console.Write("Digite o numero de linhas da primeira matriz: ");
        m = int.Parse(Console.ReadLine());

        Console.Write("Digite o numero de colunas da primeira matriz: ");
        n = int.Parse(Console.ReadLine());


        Console.Write("Digite o numero de linhas da segunda matriz: ");
        o = int.Parse(Console.ReadLine());

        Console.Write("Digite o numero de colunas da segunda matriz: ");
        p = int.Parse(Console.ReadLine());

        if (n != o)
        {
            Console.WriteLine("É impossivel a multiplicação entre essas duas matrizes");
        }
        else
        {
            

            float[,] mat1 = new float[m, n];
            float[,] mat2 = new float[o, p];

            Func.LerMatFloat(mat1);
            Func.LerMatFloat(mat2);


            float[,] resultado = MultiplicarMatrizes(mat1, mat2);

            Console.WriteLine("Resultado da multiplicação: ");
            Func.MostrarMatFloat(resultado);
        }
    }
}