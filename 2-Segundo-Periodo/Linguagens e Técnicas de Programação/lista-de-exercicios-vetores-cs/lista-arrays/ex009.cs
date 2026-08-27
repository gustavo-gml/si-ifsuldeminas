using System;
using MinhaBiblioteca;

/*Leia um vetor de caracteres. Utilize a função Length para obter a quantidade de elementos do vetor de
caracteres. Escreva o vetor lido em ordem inversa.*/

class ex009
{
    static void Main()
    {
        int n;

        Console.WriteLine("Digite a quantidade de elementos do vetor: ");
        n = int.Parse(Console.ReadLine());

        char[] vet = new char[n];

        Biblioteca.LerVetorCh(vet);
        Console.WriteLine();

        Console.WriteLine("O vetor escrito de forma inversa é: ");

        for (int i = vet.Length - 1; i >= 0; i--)
        {
            Console.WriteLine($"Elemento #{i}: {vet[i]}");
        }
    }
}