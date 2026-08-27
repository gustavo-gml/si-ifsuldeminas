package br.edu.ifsuldeminas.colabclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.Role;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(
            UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
    }

    public Usuario salvar(
            Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {

        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(
            Long id) {

        return usuarioRepository
                .findById(id)
                .orElse(null);
    }

    public Usuario buscarPorEmail(
            String email) {

        return usuarioRepository
                .findByEmail(email)
                .orElse(null);
    }

    public void tornarRepresentante(Long id){

    Usuario usuario =
            buscarPorId(id);

    if(usuario != null){

        usuario.setRole(
                Role.REPRESENTANTE);

        usuarioRepository.save(usuario);

    }

    }

    public void tornarAdmin(Long id){

        Usuario usuario =
                buscarPorId(id);

        if(usuario != null){

            usuario.setRole(
                    Role.ADMIN);

            usuarioRepository.save(usuario);

        }

    }

    public void tornarAluno(Long id){

        Usuario usuario =
                buscarPorId(id);

        if(usuario != null){

            usuario.setRole(
                    Role.ALUNO);

            usuarioRepository.save(usuario);

        }

    }

    public long quantidadeUsuarios() {

    return usuarioRepository.count();
    }
    
    public void tornarProfessor(Long id){

    Usuario usuario =
            buscarPorId(id);

    usuario.setRole(
            Role.PROFESSOR);

    usuarioRepository.save(usuario);
    }

}