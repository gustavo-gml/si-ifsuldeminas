﻿using System;
using MinhaBiblioteca;
/*prova t1*/
class exercicio1
{
	static void Preencher(int[,] matriz)
	{
		int linhas = matriz.GetLength(0);
        int cols = matriz.GetLength(1);
        Random rand = new Random();  // criando o gerador de aleatorios
        for (int i = 0; i < linhas; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                    matriz[i, j] = rand.Next(2);
            }// fim for j
        }// fim for i
	}
	
	static float vegetacao(int[,] matriz)
	{
		int linhas = matriz.GetLength(0);
        int cols = matriz.GetLength(1);
        float quant = 0;
        for (int i = 0; i < linhas; i++)
        {
            for (int j = 0; j < cols; j++)
			{
				if(matriz[i,j] == 1)
				{
					quant++;
				}
			}
		}
		return quant;
	}
	static void Main()
	{
		Console.WriteLine("Digite a quantidade de linhas da matriz: ");
		int linhas = int.Parse(Console.ReadLine());
		Console.WriteLine("Digite a quantidade de colunas da matriz: ");
		int colunas = int.Parse(Console.ReadLine());
		
		int[,] matrizAnterior = new int[linhas,colunas];
		int[,] matrizAtual = new int[linhas,colunas];
		
		Preencher(matrizAnterior);
		Preencher(matrizAtual);
		
		//linha de teste
		Console.WriteLine("Matriz Anterior");
		Biblioteca.mostrarMatriz(matrizAnterior);
		Console.WriteLine("Matriz Atual");
		Biblioteca.mostrarMatriz(matrizAtual);
		
		//Desmatado = 0
		int tamanho = linhas*colunas;
		float vegetacaoAnterior = vegetacao(matrizAnterior);
		float vegetacaoAtual = vegetacao(matrizAtual);
		float vegetacaoAnteriorP = (vegetacaoAnterior*100)/tamanho;
		float vegetacaoAtualP = (vegetacaoAtual*100)/tamanho;
		
		
		
		Console.WriteLine($"A quantidade de cobertura vegetal de 6 meses atrás foi: {vegetacaoAnterior}. A quantidade atual de cobertura vegetal é: {vegetacaoAtual}");
		Console.WriteLine();
		Console.WriteLine($"A porcentagem de cobertura vegetal de 6 meses atrás foi: {vegetacaoAnteriorP:F2}%. A porcentagem atual de cobertura vegetal é: {vegetacaoAtualP:F2}%");
		Console.WriteLine();
		
		float reducao = vegetacaoAtualP - vegetacaoAnteriorP;
		
		if(reducao > 0)
		{
			Console.WriteLine($"Houve um aumento de {reducao:F2}% na porcentagem de cobertura vegetal");
		}
		else if(reducao < 0)
		{
			Console.WriteLine($"Houve uma redução de {-reducao:F2}% na porcentagem de cobertura vegetal");
		}
		else{
			Console.WriteLine("Não houve alterações");
		}
			
	}
}