import entities.adapter.AreaCalculavel;
import entities.adapter.CalculadoraDeAreaRetangulo;
import entities.adapter.RetanguloAdapter;
import entities.decorator.*;
import entities.observer.Display;
import entities.observer.EstacaoClimatica;
import entities.template_method.RelatorioCSV;
import entities.template_method.RelatorioPDF;
import entities.template_method.RelatorioTemplate;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== TESTANDO OBSERVER (CLIMA) ===");
        EstacaoClimatica estacao = new EstacaoClimatica();
        Display display1 = new Display("Display Sala");
        Display display2 = new Display("Display Quarto");

        estacao.adicionarObserver(display1);
        estacao.adicionarObserver(display2);

        estacao.setMedicoes(25.5f, 60.0f, 1012.0f);
        System.out.println("---");
        estacao.setMedicoes(26.0f, 55.0f, 1010.5f);
        System.out.println();


        System.out.println("=== TESTANDO TEMPLATE METHOD (RELATÓRIOS) ===");
        RelatorioTemplate relatorio1 = new RelatorioPDF();
        relatorio1.gerar();

        RelatorioTemplate relatorio2 = new RelatorioCSV();
        relatorio2.gerar();


        System.out.println("=== TESTANDO ADAPTER (CALCULADORA DE ÁREA) ===");
        CalculadoraDeAreaRetangulo calculadoraVelha = new CalculadoraDeAreaRetangulo();
        AreaCalculavel adaptador = new RetanguloAdapter(calculadoraVelha, 10.0, 5.0);
        System.out.println("Área calculada via interface nova: " + adaptador.calcularArea());
        System.out.println();


        System.out.println("=== TESTANDO DECORATOR (SANDUÍCHE) ===");
        Sanduiche meuSanduiche = new SanduicheBase();
        System.out.println(meuSanduiche.getDescricao() + " | R$ " + meuSanduiche.getPreco());

        meuSanduiche = new Queijo(meuSanduiche);
        meuSanduiche = new Bacon(meuSanduiche);
        meuSanduiche = new Tomate(meuSanduiche);

        System.out.println(meuSanduiche.getDescricao() + " | R$ " + meuSanduiche.getPreco());
    }
}