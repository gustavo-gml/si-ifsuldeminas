class SidebarView {
    constructor() {
        // Seleciona os elementos da tela
        this.sidebar = document.getElementById('sidebar');
        this.toggleBtn = document.getElementById('toggle-sidebar');
    }

    // Método que o Controller vai chamar para "ouvir" o clique
    bindToggleSidebar(handler) {
        if (this.toggleBtn) {
            this.toggleBtn.addEventListener('click', handler);
        }
    }

    // Método para alterar o visual na tela
    toggleVisualState() {
        this.sidebar.classList.toggle('collapsed');
    }
}