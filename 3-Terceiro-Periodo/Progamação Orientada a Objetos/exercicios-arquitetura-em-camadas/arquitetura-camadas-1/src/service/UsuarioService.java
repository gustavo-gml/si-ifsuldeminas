package service;

import model.Usuario;
import repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(){
        this.repository = new UsuarioRepository();
    }

    public String cadastrar(String nome, String email, String telefone, String tipoDoUsuarioQueEstaCadastrando, String tipoDoUsuarioQueEstaSendoCadastradoEEsseNomePoderiaSerSoUmPouquinhoMenorMasVouSeguirOPadraoDoExercicio) {
        if (!tipoDoUsuarioQueEstaCadastrando.equalsIgnoreCase("admin")){
            return "Erro: é necessario permissão de admin para realizar cadastros";
        }
        if(repository.existePorEmail(email)){
            return "Erro: email já cadastrado";
        }
        if (!validarEmail(email)){
            return "Erro: é necessário que o email tenha o caractere '@' e depois '.'";
        }
        Usuario novo = new Usuario(nome, email, telefone, tipoDoUsuarioQueEstaSendoCadastradoEEsseNomePoderiaSerSoUmPouquinhoMenorMasVouSeguirOPadraoDoExercicio);
        repository.salvar(novo);
        return "Usuário cadastrado com sucesso";
    }

    public boolean validarEmail(String email){
        int atIndex = -1;
        // valida se tem @
        for(int i = 0; i < email.length(); i++){
            char caractere = email.charAt(i);

            if(caractere == '@'){
                atIndex = i;
                break;
            }
        }
        // valida se tem . depois do arroba
        if(atIndex != -1){
            for(int i = atIndex; i < email.length(); i++){
                char caractere = email.charAt(i);

                if(caractere == '.'){
                    return true; // Válido !
                }
            }
        }
        return false; // Não é válido !
    }

    public Usuario buscar(String email){
        return repository.findByemail(email);
    }

    public List<Usuario> listarUsuarios(){
        return repository.listar();
    }
}
