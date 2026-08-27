using System;

class ex012
{
    static float SomaMaior(float[] vet)
    {
        
        float soma = 0;

        Array.Sort(vet); /*Ordena o vetor em ordem crescente*/
        

        for (int i = 1; i < 4; i++) /*Basta ignorar o vet[0] e 0 vet[4] no for*/
        {
            soma += vet[i];
        }

        return soma;
    }
    static void Main()
    {
        string notaStr;

        notaStr = Console.ReadLine(); /*Pede os dados na mesma linha*/

        string[] vetNotaStr = notaStr.Split(' '); /*Transforma os dados digitados em um vetor de strings*/

        float[] notas = new float[vetNotaStr.Length]; /*Cria o vetor para armazenamento das notas com base no tamanho do vetor de strings*/

        for (int i = 0; i < vetNotaStr.Length; i++)
        {
            notas[i] = float.Parse(vetNotaStr[i]); /*Faz as conversões de string para int*/
        }

        float notaFinal;

        notaFinal = SomaMaior(notas);

        Console.WriteLine($"{notaFinal:F1}");
    }
}