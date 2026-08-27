using System;

namespace MinhaBiblioteca
{
    public class Biblioteca
    {

        public static void LerVetor(int[] vet)
        {
            Console.WriteLine("Digite os elementos do vetor:");
            for (int i = 0; i < vet.Length; i++)
            {
                Console.Write($"Elemento #{i}: ");
                vet[i] = int.Parse(Console.ReadLine());
            }
        }

        public static void LerVetorDb(double[] vet)
        {
            Console.WriteLine("Digite os elementos do vetor:");
            for (int i = 0; i < vet.Length; i++)
            {
                Console.Write($"Elemento #{i}: ");
                vet[i] = double.Parse(Console.ReadLine());
            }
        }

        public static void LerVetorCh(char[] vet)
        {
            Console.WriteLine("Digite os elementos do vetor:");
            for (int i = 0; i < vet.Length; i++)
            {
                Console.Write($"Elemento #{i}: ");
                vet[i] = char.Parse(Console.ReadLine());
            }
        }

        public static void RandomVet(int[] vet)
        {
            Random aleatorio = new Random();

            for (int i = 0; i < vet.Length; i++)
            {
                vet[i] = aleatorio.Next(1, 100);
            }

        }

        public static void MostrarVetor(int[] vet)
        {
            for (int i = 0; i < vet.Length; i++)
            {
                Console.WriteLine($"Elemento #{i}: {vet[i]}");
            }
        }

        public static void MostrarVetorCh(Char[] vet)
        {
            for (int i = 0; i < vet.Length; i++)
            {
                Console.Write($"[{vet[i]}]");
            }
        }
    

        public static void MostrarVetorDb(double[] vet)
        {
            for (int i = 0; i < vet.Length; i++)
            {
                Console.WriteLine($"Elemento #{i}: {vet[i]}");
            }
        }

    }
}