using System;

class ex010
{
    public static void PreencherDado(int[] vet)
        {
            Random aleatorio = new Random();

            for (int i = 0; i < vet.Length; i++)
            {
                vet[i] = aleatorio.Next(1, 7);
            }

        }
    static void Main()
    {
        int n;
        Console.WriteLine("Quantas vezes o dado será lançado ?");
        n = int.Parse(Console.ReadLine());

        int[] vetDado = new int[n];

        PreencherDado(vetDado);
        Console.WriteLine();

        int face1 = 0, face2 = 0, face3 = 0, face4 = 0, face5 = 0, face6 = 0;

        for (int i = 0; i < vetDado.Length; i++)
        {
            switch (vetDado[i])
            {
                case 1: face1++; break;
                case 2: face2++; break;
                case 3: face3++; break;
                case 4: face4++; break;
                case 5: face5++; break;
                case 6: face6++; break;
                default: Console.WriteLine("Número inválido."); break;
            }
        }
        Console.WriteLine("Após os lançamentos, foram obtidas: ");
        Console.WriteLine($"Face 1: {face1} ocorrências.");
        Console.WriteLine($"Face 2: {face2} ocorrências.");
        Console.WriteLine($"Face 3: {face3} ocorrências.");
        Console.WriteLine($"Face 4: {face4} ocorrências.");
        Console.WriteLine($"Face 5: {face5} ocorrências.");
        Console.WriteLine($"Face 6: {face6} ocorrências.");

    }
}