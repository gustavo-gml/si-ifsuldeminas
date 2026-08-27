# 🧀 The Great Cheese Hunt

Uma implementação interativa de um resolvedor de labirintos em **C#** utilizando o algoritmo de **Backtracking** (Busca em Profundidade - DFS). O projeto visualiza o personagem navegando por um mapa gerado aleatoriamente até encontrar o queijo (`Q`).

---

## 🚀 Sobre o Projeto

Este projeto foi desenvolvido para explorar os conceitos de **estruturas de dados lineares** (Pilhas) e **algoritmos de busca**. Em vez de utilizar recursividade, a lógica foi implementada de forma **iterativa**, garantindo controle total sobre o estado da navegação e permitindo a visualização passo a passo no terminal.

### 🧠 Como funciona a lógica?

O algoritmo segue os seguintes passos fundamentais:

1. **Exploração**: O personagem tenta mover-se para uma célula adjacente que contenha um caminho livre (`.`) ou o objetivo (`Q`).
2. **Registo**: Cada passo dado é armazenado em duas pilhas (`pilhaI` e `pilhaJ`), guardando as coordenadas do caminho percorrido.
3. **Backtracking**: Ao encontrar um beco sem saída, o algoritmo realiza um "passo atrás", fazendo o `Pop` das últimas posições e tentando uma nova direção a partir da célula anterior.
4. **Finalização**: O processo continua até que o queijo seja encontrado ou a pilha fique vazia (indicando que não existe caminho possível).

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** C# (.NET)
* **Ambiente:** Console Application
* **Estrutura Principal:** `Stack<int>` (Pilha) para gestão de memória do caminho.

---

## 🎮 Visualização do Mapa

O labirinto é exibido em tempo real. Abaixo, a legenda dos símbolos utilizados:

| Símbolo | Descrição |
| :--- | :--- |
| `*` / `\|` | Paredes e obstáculos intransponíveis. |
| `.` | Caminho ainda não explorado. |
| `v` | Rasto do caminho atual (Visitado). |
| `x` | Caminho explorado que resultou num beco sem saída. |
| `Q` | O Queijo (Objetivo final). |
| `(ツ)` | O explorador. |

---

## 💻 Como Executar

1. Certifique-se de ter p **SDK do .NET** instalado.
2. Clone este repositório:
   ```bash
   git clone https://github.com/gustavo-gml/backtracking-maze-solver
   
3. Altere o <TargetFramework>netXX.X</TargetFramework> no arquivo LabrintoModelo2026.csproj conforme sua versão do dotnet.

4. Inicialize o projeto no console:
   ```bash
   dotnet run
