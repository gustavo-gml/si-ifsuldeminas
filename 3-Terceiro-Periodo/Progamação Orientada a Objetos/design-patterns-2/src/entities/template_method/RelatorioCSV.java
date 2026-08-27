package entities.template_method;

public class RelatorioCSV extends RelatorioTemplate {
    @Override
    protected void extrairDados() {
        System.out.println("Extraindo dados para CSV...");
    }

    @Override
    protected void formatarDados() {
        System.out.println("Formatando dados separados por vírgula...");
    }
}
