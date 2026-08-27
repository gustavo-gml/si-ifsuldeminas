using System;
using MinhaBiblioteca;

class ex009b
{

    static void PreencheMatriz(string[] vet, int[,] mat)
    {
        string[] stringAtual = new string[4];

        int xInicial = 0, xFinal = 0, yInicial = 0, yFinal = 0;

        for (int i = 0; i < vet.Length; i++)
        {
            stringAtual = vet[i].Split();

            xInicial = int.Parse(stringAtual[0]);
            xFinal = int.Parse(stringAtual[1]);
            yInicial = int.Parse(stringAtual[2]);
            yFinal = int.Parse(stringAtual[3]);

            Console.WriteLine($"{xInicial} {yFinal} {yInicial} {xFinal}");
            for (int j = xInicial; j <= xFinal; j++)
            {
                for (int k = yInicial; k <= yFinal; k++)
                {
                    mat[j, k] = 1;
                }
            }
        }

    }

static double Quant(int[,] mat)
    {
        double count = 0;
        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (mat[i, j] == 1)
                {
                    count++;
                }
            }
        }
        return count;
    }
    
    static void Main()
    {
        int redes;
        Console.WriteLine("Digite a quantidade de redes de pesca: ");
        redes = int.Parse(Console.ReadLine());

        int linhas = Func.LinMatriz();
        int colunas = Func.ColMatriz();

        int[,] matriz = new int[linhas, colunas];

        string[] vet = new string[redes];

        for (int i = 0; i < vet.Length; i++)
        {
            vet[i] = Console.ReadLine();
        }

        PreencheMatriz(vet, matriz);

        Func.MostrarMatInt(matriz);

        double quant = Quant(matriz);
        double tamanho = matriz.GetLength(0) * matriz.GetLength(1);

        double por = (quant * 100) / tamanho;

        Console.WriteLine($"Quantidade total: {quant} \nPorcentagem ocupada: {por:F2}%");


    }
}