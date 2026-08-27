using System;
using MinhaBiblioteca;

class ex004
{
    static double maiorElemento(double[] vet)
    {
        double maior = vet[0];

        for (int i = 0; i < vet.Length; i++)
        {
            
            if (vet[i] > maior)
            {
                maior = vet[i];
            }
            
        }
        return maior;
    }

    static void Main()
    {
        int n;
        double maior;

        Console.WriteLine("Digite a quantidade de elementos do vetor: ");
        n = int.Parse(Console.ReadLine());

        double[] vetMaior = new double[n];

        Biblioteca.LerVetorDb(vetMaior);
        Console.WriteLine("");

        maior = maiorElemento(vetMaior);

        Console.WriteLine($"O maior elento do vetor é: {maior:F2}");

    }
    

}