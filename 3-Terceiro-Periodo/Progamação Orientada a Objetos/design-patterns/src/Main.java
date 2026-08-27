import entities.builder.Pizza;
import entities.factory.FormaGeometrica;
import entities.factory.FormaGeometricaFactory;
import entities.singleton.Logger;
import entities.strategy.CarrinhoCompras;
import entities.strategy.DescontoBlackFriday;
import entities.strategy.DescontoNatal;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== TESTANDO SINGLETON (LOGGER) ===");
        // Testament "criar" dois loggers
        Logger log1 = Logger.getInstancia();
        Logger log2 = Logger.getInstancia();

        // Escrevendo no arquivo
        log1.registrar("Iniciando o sistema...");
        log2.registrar("Criando pedido no carrinho...");

        // Verifica se as duas variáveis apontam para o MESMO espaço na memória
        System.out.println("log1 é a mesma instância que log2? " + (log1 == log2));
        System.out.println("Verifique o arquivo 'sistema_log.txt' na pasta do seu projeto!\n");


        System.out.println("=== TESTANDO FACTORY (FORMAS GEOMÉTRICAS) ===");
        FormaGeometricaFactory fabrica = new FormaGeometricaFactory();

        FormaGeometrica forma1 = fabrica.criarForma("CIRCULO");
        forma1.desenhar();

        FormaGeometrica forma2 = fabrica.criarForma("QUADRADO");
        forma2.desenhar();
        System.out.println();


        System.out.println("=== TESTANDO STRATEGY (DESCONTOS) ===");
        CarrinhoCompras carrinho = new CarrinhoCompras(1000.00); // Carrinho de R$ 1000

        System.out.println("Total sem desconto: R$ " + carrinho.calcularTotal());

        carrinho.setEstrategiaDesconto(new DescontoNatal());
        System.out.println("Total com desconto de Natal: R$ " + carrinho.calcularTotal());

        carrinho.setEstrategiaDesconto(new DescontoBlackFriday());
        System.out.println("Total com desconto de Black Friday: R$ " + carrinho.calcularTotal());
        System.out.println();


        System.out.println("=== TESTANDO BUILDER (PIZZA) ===");
        // Criando uma pizza apenas com queijo extra
        Pizza pizza1 = new Pizza.Builder("Família")
                .adicionarQueijoExtra()
                .build();

        // Criando uma pizza completassa
        Pizza pizza2 = new Pizza.Builder("Média")
                .adicionarBordaRecheada()
                .adicionarMolhoEspecial()
                .adicionarQueijoExtra()
                .build();

        System.out.println("Pizza 1: Tamanho " + pizza1.getTamanho() +
                " | Queijo Extra: " + pizza1.temQueijoExtra() +
                " | Borda: " + pizza1.temBordaRecheada());

        System.out.println("Pizza 2: Tamanho " + pizza2.getTamanho() +
                " | Queijo Extra: " + pizza2.temQueijoExtra() +
                " | Borda: " + pizza2.temBordaRecheada() +
                " | Molho: " + pizza2.temMolhoEspecial());
    }
}