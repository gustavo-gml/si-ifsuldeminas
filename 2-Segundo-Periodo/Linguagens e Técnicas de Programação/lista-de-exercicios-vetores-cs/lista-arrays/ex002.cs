using System;
using MinhaBiblioteca;
class ex002
{
    static int imparQuant(int[] vet)
    {
        int quant = 0;
        for (int i = 0; i < vet.Length; i++)
        {
            if (vet[i] % 2 != 0)
            {
                quant++;
            }
        }
        return quant;
    }

    static void Main()
    {
        int n, impar;

        Console.WriteLine("Digite a quantidade de elementos do vetor: ");
        n = int.Parse(Console.ReadLine());

        int[] vet = new int[n];

        Biblioteca.LerVetor(vet);

        impar = imparQuant(vet);

        Console.WriteLine($"Foram digitados {impar} números impares.");
    }
}

