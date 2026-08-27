using System;
using MinhaBiblioteca;

class ex008
{
    static int Ocorrencia(int[] vet, int num)
    {
        int repeated = 0;

        for (int i = 0; i < vet.Length; i++)
        {
            if (vet[i] == num)
            {
                repeated++;
            }
        }
        return repeated;
    }
    static void Main()
    {
        int n, check, repeated;

        Console.WriteLine("Digite a quantidade de elementos do vetor: ");
        n = int.Parse(Console.ReadLine());

        int[] vet = new int[n];

        Biblioteca.LerVetor(vet);

        Console.WriteLine("Digite um valor para verificação de ocorrência: ");
        check = int.Parse(Console.ReadLine());
        Console.WriteLine();

        repeated = Ocorrencia(vet, check);

        Console.WriteLine($"O número {check} aparece {repeated} vezes no vetor");
        Console.WriteLine();
        Biblioteca.MostrarVetor(vet);
        

    }
}