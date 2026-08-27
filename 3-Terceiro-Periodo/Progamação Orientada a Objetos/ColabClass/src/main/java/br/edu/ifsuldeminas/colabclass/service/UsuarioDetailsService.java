package br.edu.ifsuldeminas.colabclass.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.Usuario;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public UsuarioDetailsService(
            UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        Usuario usuario =
                usuarioService.buscarPorEmail(email);

        if (usuario == null) {

            throw new UsernameNotFoundException(
                    "Usuário não encontrado");

        }

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getRole().name())
                .build();

    }

}