using System;

class Ex1180
{
    static void Main(string[] args)
    {
        int N = int.Parse(Console.ReadLine());
        int[] vet = new int[N];

        string valStr = Console.ReadLine();

        string[] valInt = valStr.Split(' ');

        for (int i = 0; i < vet.Length; i++)
        {
            vet[i] = int.Parse(valInt[i]);
        }

        int menor = vet[0], pos = 0;
        for (int i = 0; i < vet.Length; i++)
        {
            if (vet[i] < menor)
            {
                menor = vet[i];
                pos = i;
            }
        }

        Console.WriteLine($"Menor valor: {menor}");
        Console.WriteLine($"Posicao: {pos}");
    }
}