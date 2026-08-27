using System;
using MinhaBiblioteca;

class ex007
{
    static double[] MultVet(double[] vet1, double[] vet2)
    {
        double[] vetResult = new double[vet1.Length];

        for (int i = 0; i < vet1.Length; i++)
        {
            vetResult[i] = vet1[i] * vet2[i];
        }
        return vetResult;
    }

    static void Main()
    {
        int n;
        Console.WriteLine("Digite a quantidade de elementos dos vetores: ");
        n = int.Parse(Console.ReadLine());

        double[] vet1 = new double[n];
        double[] vet2 = new double[n];
        double[] vetResult = new double[n];

        Biblioteca.LerVetorDb(vet1);
        Biblioteca.LerVetorDb(vet2);

        vetResult = MultVet(vet1, vet2);
        Console.WriteLine();

        Console.WriteLine("Vetor resultante: ");
        Biblioteca.MostrarVetorDb(vetResult);
    }
}