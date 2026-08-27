using System;
using MinhaBiblioteca;

class ex008
{
    static void PreencheCord(string[] vet, int[,] mat)
    {
        /*Validação de entrada básica, se o usuario digitar um dado que exede o limite das linhas ou das colunas da matriz, o programa mostra uma mensagem de erro e repete entrada de dados*/
        int maximoLinha = mat.GetLength(0) - 1;
        int maximoColuna = mat.GetLength(1) - 1;

        /*Recebe uma substring da posição analisada do vetor, serve para a validação*/
        string[] validacao = new string[2];
        int linhaAtual, colunaAtual;

        for (int i = 0; i < vet.Length; i++)
        {
            vet[i] = Console.ReadLine();

            validacao = vet[i].Split(' ');

            linhaAtual = int.Parse(validacao[0]);
            colunaAtual = int.Parse(validacao[1]);

            
            if (linhaAtual > maximoLinha || colunaAtual > maximoColuna)/*se o dado exceder o limite da matriz: */
            {
                Console.WriteLine();

                i--;/*Repete a iteração atual*/

                Console.WriteLine($"Voce excedeu o tamanho de sua matriz! As coordenadas devem ir até no máximo {maximoLinha} {maximoColuna}. Por favor, insira novamente coordenadas válidas ( A leitura não será reiniciada, apenas insira novamente o dado que foi inserido incorretamente e continue a partir daí normalmente).");

                Console.WriteLine();
            }

        }
    }

    static void PreencheOcorrencia(int[,] mat, string[] vet)
    {
        /*Atualiza as coordenasdas informadas pelo usuário na matriz em +1*/
        int linha, coluna;
        string[] xyAtual = new string[2];

        for (int i = 0; i < vet.Length; i++)
        {
            xyAtual = vet[i].Split(' ');
            linha = int.Parse(xyAtual[0]);
            coluna = int.Parse(xyAtual[1]);

            mat[linha,coluna]++;
        }
    }

    static int DupliRaio(int[,] mat)/*Verifica se mais de um raio caiu na mesma coordenada*/
    {
        for (int i = 0; i < mat.GetLength(0); i++)
        {
            for (int j = 0; j < mat.GetLength(1); j++)
            {
                if (mat[i, j] > 1)
                {
                    return 1;/*Se achar, a função encerra e retorna 1*/
                }
            }
        }
        return 0; /*Se o if não se concretizar, retorna 0*/
    }

    static void Main()
    {
        /*Solicita a extensão da matriz*/
        int linhas = Func.LinMatriz();
        int colunas = Func.ColMatriz();

        /*Cria a matriz*/
        int[,] mapa = new int[linhas, colunas];

        /*Solicita a quantidade de raios que cairam*/
        Console.WriteLine("Digite a quantidade de ocorrências: ");
        int ocorrencias = int.Parse(Console.ReadLine());

        /*Cria um vetor para ser parâmetro da função de preenchimento e contagem*/
        string[] coordenadas = new string[ocorrencias];
        /*Ele será um vetor de string devido a entrada de coordenadas ser na mesma linha */

        Console.WriteLine("Digite as coordenadas de cada ocorrência na mesma linha (ex: 1 1): ");

        /*Chama a função, que preenche as coordenadas das ocorrências de raios*/
        PreencheCord(coordenadas, mapa);

        /*Chama a função que prenche a matriz com as ocorrencias de raios*/
        PreencheOcorrencia(mapa, coordenadas);

        /*Chama a função que retorna se um raio caiu alguma vez em um mesmo lugar dentro do Console.WriteLine */
        Console.WriteLine();
        Console.WriteLine($"{DupliRaio(mapa)}");
        
    }
}