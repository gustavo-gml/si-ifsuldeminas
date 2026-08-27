public class Main{
    public static void main(String[] args) {

        // 1. Dado um número inteiro, verifique e imprima se ele é par ou ímpar.
        int numeroParImpar = 17;
        System.out.println("--- Verificação de Par ou Ímpar ---");
        if (numeroParImpar % 2 == 0) {
            System.out.println("O número " + numeroParImpar + " é par.");
        } else {
            System.out.println("O número " + numeroParImpar + " é ímpar.");
        }

        // 2. Determine o maior dentre 3 números.
        int v1 = 45, v2 = 82, v3 = 31;
        System.out.println("\n--- Maior de Três Números ---");
        int maior = v1;
        if (v2 > maior) maior = v2;
        if (v3 > maior) maior = v3;
        System.out.println("Entre " + v1 + ", " + v2 + " e " + v3 + ", o maior é: " + maior);

        // 3. Verifique se um ano é bissexto (Divisível por 4 e não por 100, ou divisível por 400).
        int ano = 2024;
        System.out.println("\n--- Verificação de Ano Bissexto ---");
        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
            System.out.println(ano + " é um ano bissexto.");
        } else {
            System.out.println(ano + " não é um ano bissexto.");
        }

        // 4. Calculadora usando dois números e um operador (+, -, *, /).
        double n1 = 10, n2 = 2;
        char operador = '*';
        System.out.println("\n--- Calculadora Simples ---");
        System.out.print("Operação: " + n1 + " " + operador + " " + n2 + " = ");
        if (operador == '+') {
            System.out.println(n1 + n2);
        } else if (operador == '-') {
            System.out.println(n1 - n2);
        } else if (operador == '*') {
            System.out.println(n1 * n2);
        } else if (operador == '/') {
            if (n2 != 0) System.out.println(n1 / n2);
            else System.out.println("Erro: Divisão por zero.");
        }

        // 5. Sistema de login com múltiplas combinações de erro.
        String usuarioCadastrado = "gustavo_turing";
        String senhaCadastrada = "java123";

        String usuarioInput = "gustavo_turing";
        String senhaInput = "senhaErrada";

        System.out.println("\n--- Sistema de Login ---");
        boolean usuarioCorreto = usuarioInput.equals(usuarioCadastrado);
        boolean senhaCorreta = senhaInput.equals(senhaCadastrada);

        if (usuarioCorreto && senhaCorreta) {
            System.out.println("Acesso permitido");
        } else if (!usuarioCorreto && senhaCorreta) {
            System.out.println("Acesso negado usuário");
        } else if (usuarioCorreto && !senhaCorreta) {
            System.out.println("Acesso negado senha");
        } else {
            System.out.println("Acesso negado usuário e senha");
        }
    }
}