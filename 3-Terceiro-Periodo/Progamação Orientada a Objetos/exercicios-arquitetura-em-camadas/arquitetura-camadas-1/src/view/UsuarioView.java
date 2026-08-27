package view;

import model.Usuario;

import java.util.List;

public class UsuarioView {
    public void exibirMensagem(String msg){
        System.out.println(msg);
    }



    public void exibirUsuarios(List<Usuario> usuarios){
        for(Usuario u: usuarios){
            System.out.println(u);
        }
    }

    public void exibirUsuarioBusca(Usuario u){
        System.out.print(u);
    }
}
