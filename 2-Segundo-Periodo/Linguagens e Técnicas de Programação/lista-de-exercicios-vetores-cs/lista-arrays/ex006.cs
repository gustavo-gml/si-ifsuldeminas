using System;
using MinhaBiblioteca;

class ex006
{

    static void Main()
    {
        int n;
        Console.WriteLine("Quantos números aleatórios devem ser armazenados ?");
        n = int.Parse(Console.ReadLine());

        int[] numeros = new int[n];

        Biblioteca.RandomVet(numeros);
        

        int guess;
        bool status = false;

        Console.WriteLine("Digite um número inteiro: ");
        guess = int.Parse(Console.ReadLine());

        for (int i = 0; i < numeros.Length; i++)
        {
            if (guess == numeros[i])
            {
                Console.WriteLine($"O numero {guess} está presente na posição #{i} do vetor !");
                status = true;
                break;
            }
        }
        if (status == false)
        {
            Console.WriteLine($"O número {guess} não está presente no vetor.");
        }
    }
}