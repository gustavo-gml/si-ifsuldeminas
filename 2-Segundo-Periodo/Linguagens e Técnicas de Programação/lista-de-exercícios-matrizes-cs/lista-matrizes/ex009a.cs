using System;
using MinhaBiblioteca;

class ex009a
{
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
        int linhas, cols;
        int xi, xf, yi, yf;
        int quantidadeRedes;
        Console.Write("Entre com a quantidade de linhas e colunas:");
        linhas = int.Parse(Console.ReadLine());
        cols = int.Parse(Console.ReadLine());
        int[,] matrixMar = new int[linhas, cols];
        Func.MostrarMatInt(matrixMar);

        Console.Write("Entre com a quantidade de redes:");
        quantidadeRedes = int.Parse(Console.ReadLine());

        for (int cont = 1; cont <= quantidadeRedes; cont++) {

            Console.WriteLine($"--- Entre com as coordenadas da rede {cont} ---");
            Console.WriteLine("Xi e Xf");
            xi = int.Parse(Console.ReadLine());
            xf = int.Parse(Console.ReadLine());

            Console.WriteLine("Yi e Yf");
            yi = int.Parse(Console.ReadLine());
            yf = int.Parse(Console.ReadLine());

            for (int i = xi; i <= xf; i++)
                for (int j = yi; j <= yf; j++)
                    matrixMar[i, j] = 1;

        }//  fim for quantidadeRedes
        Func.MostrarMatInt(matrixMar);

        double quant = Quant(matrixMar);
        double tamanho = matrixMar.GetLength(0)* matrixMar.GetLength(1);

        double por = (quant * 100) / tamanho;

        Console.WriteLine($"Quantidade total: {quant} \nPorcentagem ocupada: {por:F2}%");



    }

}