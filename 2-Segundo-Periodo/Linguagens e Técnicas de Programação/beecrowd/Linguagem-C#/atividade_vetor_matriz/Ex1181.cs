using System;

class Ex1181
{
    static float SomaLinha(float[,] mat, int L)
    {
        float soma = 0;

        for (int i = 0; i < mat.GetLength(1); i++)
        {
            soma += mat[L, i];
        }

        return soma;
    }

    static float MediaLinha(float[,] mat, int L)
    {
        float media = 0;

        for (int i = 0; i < mat.GetLength(1); i++)
        {
            media += mat[L, i];
        }

        return media/mat.GetLength(1);
    }
    
    static void Main(string[] args)
    {
        int L = int.Parse(Console.ReadLine());

        char operation = char.Parse(Console.ReadLine());

        float[,] mat = new float[12, 12];

        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                mat[i, j] = float.Parse(Console.ReadLine());
            }
        }


        switch (operation)
        {
            case 'S':
                Console.WriteLine($"{SomaLinha(mat, L):F1}");
                break;

            case 'M':
                Console.WriteLine($"{MediaLinha(mat, L):F1}");
                break;
        }
    }
}
