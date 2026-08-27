using System;
using MinhaBiblioteca;

class ex007
{
    static void somaMat(int[,] mat1, int[,] mat2, int[,] matOp)
    {
        for (int i = 0; i < mat1.GetLength(0); i++)
        {
            for (int j = 0; j < mat1.GetLength(1); j++)
            {
                matOp[i, j] = mat1[i, j] + mat2[i, j];
            }
        }
    }

    static void subMat(int[,] mat1, int[,] mat2, int[,] matOp)
    {
        for (int i = 0; i < mat1.GetLength(0); i++)
        {
            for (int j = 0; j < mat1.GetLength(1); j++)
            {
                matOp[i, j] = mat1[i, j] - mat2[i, j];
            }
        }
    }

    static void sconst(int[,] mat1, int[,] mat2, int cons)
    {
        for (int i = 0; i < mat1.GetLength(0); i++)
        {
            for (int j = 0; j < mat1.GetLength(1); j++)
            {
                mat1[i, j] += cons;
                mat2[i, j] += cons;
            }
        }
    }


    static void Main()
    {
        int linhas = Func.LinMatriz();
        int colunas = Func.ColMatriz();

        int[,] mat1 = new int[linhas, colunas];
        int[,] mat2 = new int[linhas, colunas];
        int[,] matOp = new int[linhas, colunas];

        bool op = false;

        Func.GerarMatInt(mat1);
        Func.GerarMatInt(mat2);

        while (true)
        {

        Console.WriteLine("Digite a letra correspondente a opção que você deseja realizar:\n(a) somar as duas matrizes\n(b) subtrair a primeira matriz da segunda\n(c) adicionar uma constante as duas matrizes\n(d) imprimir as matrizes.\n(e) encerrar o programa");

        


            char operacao = char.Parse(Console.ReadLine());

            operacao = char.ToLower(operacao);

            int constante;

            if (operacao == 'a')
            {
                somaMat(mat1, mat2, matOp);
                op = true;
            }
            else if (operacao == 'b')
            {
                subMat(mat1, mat2, matOp);
                op = true;
            }
            else if (operacao == 'c')
            {
                Console.WriteLine("Qual constante deve ser adicionada nas matrizes ?");
                constante = int.Parse(Console.ReadLine());
                sconst(mat1, mat2, constante);
                op = false;
            }
            else if (operacao == 'd')
            {
                Func.MostrarMatInt(mat1);
                Func.MostrarMatInt(mat2);
                if (op)
                {
                    Console.WriteLine("Esse é o resultado da operação escolhida: ");
                    Func.MostrarMatInt(matOp);
                }
            }
            else if (operacao == 'e')
            {
                Console.WriteLine("--PROGRAMA ENCERRADO--");
                break;
            }

        }

    }
}