using System;
using MinhaBiblioteca;

class ex005
{
    static void dnacomp(char[] vet, char[] vetComplementar) //A=T C=G
    {
        for (int i = 0; i < vet.Length; i++)
        {
            if (vet[i] == 'A')
            {
                vetComplementar[i] = 'T';
            }
            else if (vet[i] == 'T')
            {
                vetComplementar[i] = 'A';
            }
            else if (vet[i] == 'C')
            {
                vetComplementar[i] = 'G';
            }
            else if (vet[i] == 'G')
            {
                vetComplementar[i] = 'C';
            }
        }
    }
    static void Main()
    {
        int n;
        Console.WriteLine("Qual será o tamanho do vetor (max: 50) ?");
        n = int.Parse(Console.ReadLine());

        while (n > 50)
        {
            Console.WriteLine("Insira um número válido (max: 50): ");
            n = int.Parse(Console.ReadLine());
        }

        char[] dna = new char[n];
        char[] dnaComplementar = new char[n];

        Biblioteca.LerVetorCh(dna);
        dnacomp(dna, dnaComplementar);

        
        Console.Write("Vetor complementar gerado: ");
        Biblioteca.MostrarVetorCh(dnaComplementar);





    }
}