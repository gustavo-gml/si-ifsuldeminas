/*R x C, onde R representa o número de regiões (linhas) e C o número 
de cidades (colunas) em cada região.*/

using System;
using MinhaBiblioteca;

class ex010
{
    static void MostrarTropinha(int[,] mat)
    {
        for (int i = 0; i < mat.GetLength(0); i++)
        {
            Console.Write($"Região {i + 1}:");
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                Console.Write($" {mat[i, j]} ");
            }
            Console.WriteLine();
        }
    }

    static void MostrarForca(int[,] mat)
    {
        int soma = 0;

        for (int i = 0; i < mat.GetLength(0); i++)
        {
            Console.Write($"Região {i + 1}:");
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                soma += mat[i, j];
            }
            Console.Write($" {soma} tropas\n");
            soma = 0;
        }
    }
    static void Main()
    {
        int linhas, colunas;

        Console.Write("Digite a quantidae de regiões: ");
        linhas = int.Parse(Console.ReadLine());

        Console.Write("Digite a quantidae de cidades em cada região: ");
        colunas = int.Parse(Console.ReadLine());

        int[,] tropinhas = new int[linhas, colunas];
        Func.GerarMatInt(tropinhas);

        Console.WriteLine("Matriz das tropas (Quantidade de tropas por cidade)");

        MostrarTropinha(tropinhas);
        Console.WriteLine();

        Console.WriteLine("Força Total das Regiões: ");
        MostrarForca(tropinhas);




    }
}