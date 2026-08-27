using System;
using MinhaBiblioteca;

class ex001
{
    static int somaVet(int[] vet)
    {
        int soma = 0;
        for (int i = 0; i < vet.Length; i++)
        {
            soma += vet[i];
        }
        return soma;
    }

    static void Main()
    {
        int n, resultado;
        Console.WriteLine("Digite a quantidade de elementos do vetor: ");
        n = int.Parse(Console.ReadLine()); //pergunta ao usuário o tamanho do vetor

        int[] vet = new int[n];
        Biblioteca.LerVetor(vet); //usa a função lerVet para preencher o vetor
        //Biblioteca.RandomVet(vet); //poderia ser usado para testes mais rápidos

        resultado = somaVet(vet); //usa a função somaVet para percorrer e somar os elementos do vetor

        Console.WriteLine($"A soma dos elementos do vetor é: {resultado}"); // printa o resultado
    }
}