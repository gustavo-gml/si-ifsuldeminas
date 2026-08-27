class SidebarController {
    constructor(view) {
        this.view = view;

        // Avisa a view qual função deve ser executada quando o botão for clicado
        this.view.bindToggleSidebar(this.handleToggle.bind(this));
    }

    // A lógica real do que acontece no clique
    handleToggle() {
        // Como não temos um "Model" estrito para salvar no banco de dados se o menu
        // está aberto ou fechado, o controller apenas manda a View se atualizar visualmente. futuramente lógica de bd
        
        this.view.toggleVisualState();
    }
}

// Inicializa tudo quando o HTML carregar
document.addEventListener('DOMContentLoaded', () => {
    const view = new SidebarView();
    const controller = new SidebarController(view);
});