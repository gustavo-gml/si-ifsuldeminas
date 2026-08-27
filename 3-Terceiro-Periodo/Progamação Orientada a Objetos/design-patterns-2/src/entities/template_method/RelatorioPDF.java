package entities.template_method;

public class RelatorioPDF extends RelatorioTemplate {
    @Override
    protected void extrairDados() {
        System.out.println("Extraindo dados para PDF...");
    }

    @Override
    protected void formatarDados() {
        System.out.println("Formatando dados em layout PDF...");
    }
}
