using System;
using System.IO;

namespace MatrizGrafo
{
    class Grafo
    {
        // Implementação da classe Grafo usando matriz de adjacência
        private int[,] matrizAdjacencia;
        private int numeroDeVertices;
        public Grafo(int vertices)
        {
            numeroDeVertices = vertices;
            matrizAdjacencia = new int[vertices, vertices];
        }
        
        public void adicionarAresta(int origem, int destino)
        {
            matrizAdjacencia[origem, destino] = 1;
        }
        
        public void removerAresta(int origem, int destino)
        {
            matrizAdjacencia[origem, destino] = 0;
        }

        public bool carregarMatrizDeArquivo(string caminhoArquivo)
        {
            try
            {
                string[] linhas = File.ReadAllLines(caminhoArquivo);

                for (int i = 0; i < numeroDeVertices && i < linhas.Length; i++)
                {
                    string linha = linhas[i].Trim();
                    string[] valores = linha.Split(',');

                    for (int j = 0; j < numeroDeVertices && j < valores.Length; j++)
                    {
                        matrizAdjacencia[i, j] = int.Parse(valores[j]);
                    }
                }
                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Erro ao carregar arquivo: {ex.Message}");
                return false;
            }
        }

        public void mostrarMatriz()
        {
            Console.Write("   ");
            for (int i = 0; i < numeroDeVertices; i++)
                Console.Write($"{i} ");
            Console.WriteLine();
            
            for (int i = 0; i < numeroDeVertices; i++)
            {
                Console.Write($"{i}: ");
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    Console.Write(matrizAdjacencia[i, j] + " ");
                }
                Console.WriteLine();
            }
        }

        public bool eReflexiva()
        {
            for (int i = 0; i < numeroDeVertices; i++)
            {
                if (matrizAdjacencia[i, i] != 1)
                {
                    return false;
                }
            }
            return true;
        }
        
        public void mostrarFalhaReflexiva()
        {
            Console.WriteLine("\n=== Verificação de Reflexividade ===");
            bool falhas = false;
            for (int i = 0; i < numeroDeVertices; i++)
            {
                if (matrizAdjacencia[i, i] != 1)
                {
                    Console.WriteLine($"Vértice {i}: Diagonal principal = {matrizAdjacencia[i, i]} (deveria ser 1)");
                    falhas = true;
                }
            }
            if (!falhas)
            {
                Console.WriteLine("O grafo é reflexivo!");
            }
        }

        public bool eSimetrica()
        {
            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = i + 1; j < numeroDeVertices; j++)
                {
                    if (matrizAdjacencia[i, j] != matrizAdjacencia[j, i])
                    {
                        return false;
                    }
                }
            }
            return true;
        }

        public void mostrarFalhaSimetrica()
        {
            Console.WriteLine("\n=== Verificação de Simetria ===");
            bool falhas = false;
            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = i + 1; j < numeroDeVertices; j++)
                {
                    if (matrizAdjacencia[i, j] != matrizAdjacencia[j, i])
                    {
                        Console.WriteLine($"Aresta ({i},{j}) = {matrizAdjacencia[i, j]}, mas Aresta ({j},{i}) = {matrizAdjacencia[j, i]}");
                        falhas = true;
                    }
                }
            }
            if (!falhas)
            {
                Console.WriteLine("O grafo é simétrico!");
            }
        }

        public int[,] obterCaminho2()
        {
            int[,] caminho2 = new int[numeroDeVertices, numeroDeVertices];
            
            // Multiplicação booleana de matrizes: R² = R ∘ R
            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    caminho2[i, j] = 0;
                    for (int k = 0; k < numeroDeVertices; k++)
                    {
                        // Operação booleana: R[i,k] AND R[k,j]
                        if (matrizAdjacencia[i, k] == 1 && matrizAdjacencia[k, j] == 1)
                        {
                            caminho2[i, j] = 1;
                            break; // Basta encontrar um caminho
                        }
                    }
                }
            }
            return caminho2;
        }

        public void mostrarCaminho2()
        {
            Console.WriteLine("\n=== Caminhos de Comprimento 2 (R²) ===");
            int[,] caminho2 = obterCaminho2();
            
            Console.Write("   ");
            for (int i = 0; i < numeroDeVertices; i++)
                Console.Write($"{i} ");
            Console.WriteLine();
            
            for (int i = 0; i < numeroDeVertices; i++)
            {
                Console.Write($"{i}: ");
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    Console.Write(caminho2[i, j] + " ");
                }
                Console.WriteLine();
            }
            
            // Mostrar os caminhos específicos
            Console.WriteLine("\nCaminhos específicos de comprimento 2:");
            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    if (caminho2[i, j] == 1)
                    {
                        Console.Write($"({i}→{j}): ");
                        bool primeiro = true;
                        for (int k = 0; k < numeroDeVertices; k++)
                        {
                            if (matrizAdjacencia[i, k] == 1 && matrizAdjacencia[k, j] == 1)
                            {
                                if (!primeiro) Console.Write(", ");
                                Console.Write($"{i}→{k}→{j}");
                                primeiro = false;
                            }
                        }
                        Console.WriteLine();
                    }
                }
            }
        }

        public bool verificarTransitividade()
        {
            int[,] caminho2 = obterCaminho2();
            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    // Se há caminho de comprimento 2, deve haver aresta direta
                    if (caminho2[i, j] == 1 && matrizAdjacencia[i, j] == 0)
                    {
                        return false;
                    }
                }
            }
            return true;
        }

        public void mostrarFalhaTransitividade()
        {
            Console.WriteLine("\n=== Verificação de Transitividade ===");
            int[,] caminho2 = obterCaminho2();
            bool falhas = false;
            
            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    if (caminho2[i, j] == 1 && matrizAdjacencia[i, j] == 0)
                    {
                        Console.WriteLine($"Existe caminho de comprimento 2 de {i} para {j}, mas não há aresta direta!");
                        // Encontrar o caminho intermediário
                        for (int k = 0; k < numeroDeVertices; k++)
                        {
                            if (matrizAdjacencia[i, k] == 1 && matrizAdjacencia[k, j] == 1)
                            {
                                Console.WriteLine($"  Caminho encontrado: {i} → {k} → {j}");
                            }
                        }
                        falhas = true;
                    }
                }
            }
            
            if (!falhas)
            {
                Console.WriteLine("O grafo é transitivo!");
            }
        }

        public int[,] obterRInfinito()
        {
            int[,] rInfinito = new int[numeroDeVertices, numeroDeVertices];
            // Inicializa R infinito com a matriz de adjacência
            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    rInfinito[i, j] = matrizAdjacencia[i, j];
                }
            }

            // Aplica o algoritmo de Warshall para calcular R infinito
            for (int k = 0; k < numeroDeVertices; k++)
            {
                for (int i = 0; i < numeroDeVertices; i++)
                {
                    for (int j = 0; j < numeroDeVertices; j++)
                    {
                        rInfinito[i, j] = rInfinito[i, j] | (rInfinito[i, k] & rInfinito[k, j]);
                    }
                }
            }
            return rInfinito;
        }

        public void mostrarRInfinito()
        {
            Console.WriteLine("\n=== R∞ (Fecho Transitivo) ===");
            int[,] rInfinito = obterRInfinito();
            
            Console.Write("   ");
            for (int i = 0; i < numeroDeVertices; i++)
                Console.Write($"{i} ");
            Console.WriteLine();
            
            for (int i = 0; i < numeroDeVertices; i++)
            {
                Console.Write($"{i}: ");
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    Console.Write(rInfinito[i, j] + " ");
                }
                Console.WriteLine();
            }
        }

        public int[,] obterMatrizConexividade()
        {
            int[,] conexividade = new int[numeroDeVertices, numeroDeVertices];
            int[,] rInfinito = obterRInfinito();

            for (int i = 0; i < numeroDeVertices; i++)
            {
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    conexividade[i, j] = (rInfinito[i, j] == 1 || rInfinito[j, i] == 1) ? 1 : 0;
                }
            }
            return conexividade;
        }

        public void mostrarMatrizConexividade()
        {
            Console.WriteLine("\n=== Matriz de Conexividade ===");
            int[,] conexividade = obterMatrizConexividade();
            
            Console.Write("   ");
            for (int i = 0; i < numeroDeVertices; i++)
                Console.Write($"{i} ");
            Console.WriteLine();
            
            for (int i = 0; i < numeroDeVertices; i++)
            {
                Console.Write($"{i}: ");
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    Console.Write(conexividade[i, j] + " ");
                }
                Console.WriteLine();
            }
        }

        public void desenharGrafoASCII()
        {
            Console.WriteLine("\n=== Representação do Grafo R ===");
            for (int i = 0; i < numeroDeVertices; i++)
            {
                Console.Write($"Vértice {i}: ");
                bool temArestas = false;
                for (int j = 0; j < numeroDeVertices; j++)
                {
                    if (matrizAdjacencia[i, j] == 1)
                    {
                        if (temArestas) Console.Write(", ");
                        Console.Write($"→ {j}");
                        temArestas = true;
                    }
                }
                if (!temArestas) Console.Write("(sem arestas de saída)");
                Console.WriteLine();
            }
        }

        
    }
}