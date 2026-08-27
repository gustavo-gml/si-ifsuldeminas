package repository;

import model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private static List<Usuario> banco = new ArrayList<>();

    public void salvar(Usuario usuario){
        banco.add(usuario);
    }

    public boolean existePorEmail(String email){
        for(Usuario u : banco){
            if (u.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    public Usuario findByemail(String email){
        for(Usuario u : banco){
            if (u.getEmail().equals(email)){
                System.out.println("Usuario '" + u.getNome() + "' foi encontrado !");
                return u;
            }
        }
        System.out.print("Nenhum usuário foi encontrado com o email '" + email + "'");
        return null;
    }

    public List<Usuario> listar(){
        return new ArrayList<>(banco);
    }

}
