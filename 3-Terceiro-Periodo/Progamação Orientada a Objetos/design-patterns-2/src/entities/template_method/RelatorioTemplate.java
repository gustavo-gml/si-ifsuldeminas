package entities.template_method;

public abstract class RelatorioTemplate {
    public final void gerar() {
        abrirConexao();
        extrairDados();
        formatarDados();
        fecharConexao();
    }

    private void abrirConexao() {
        System.out.println("Abrindo conexão com o banco de dados...");
    }

    protected abstract void extrairDados();

    protected abstract void formatarDados();

    private void fecharConexao() {
        System.out.println("Fechando conexão com o banco de dados...\n");
    }
}