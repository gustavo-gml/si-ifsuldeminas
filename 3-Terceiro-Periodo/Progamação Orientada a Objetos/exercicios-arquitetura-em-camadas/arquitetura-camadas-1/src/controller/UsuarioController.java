package controller;

import model.Usuario;
import service.UsuarioService;
import view.UsuarioView;

import java.util.List;

public class UsuarioController {
    private UsuarioService service;
    private UsuarioView view;

    public UsuarioController(){
        this.service = new UsuarioService();
        this.view = new UsuarioView();
    }

    public void cadastrar(String nome, String email, String telefone, String tipoDoUsuarioQueEstaCadastrando, String tipoDoUsuarioQueEstaSendoCadastradoEEsseNomePoderiaSerSoUmPouquinhoMenorMasVouSeguirOPadraoDoExercicio){
        String resultado = service.cadastrar(nome, email, telefone, tipoDoUsuarioQueEstaCadastrando, tipoDoUsuarioQueEstaSendoCadastradoEEsseNomePoderiaSerSoUmPouquinhoMenorMasVouSeguirOPadraoDoExercicio);
        view.exibirMensagem(resultado);
    }

    public void listar(){
        List<Usuario> usuarios = service.listarUsuarios();
        view.exibirUsuarios(usuarios);
    }

    public void buscar(String email){
        Usuario Usuariobusca = service.buscar(email);

        if (Usuariobusca == null)
            return;

        view.exibirUsuarioBusca(Usuariobusca);

    }
}
