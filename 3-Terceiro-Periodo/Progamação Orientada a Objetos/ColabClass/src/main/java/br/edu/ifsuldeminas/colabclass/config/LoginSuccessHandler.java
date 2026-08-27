package br.edu.ifsuldeminas.colabclass.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UsuarioService usuarioService;

    public LoginSuccessHandler(
            UsuarioService usuarioService) {

        this.usuarioService = usuarioService;
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        switch (usuario.getRole()) {

            case ADMIN:

                response.sendRedirect("/admin");
                break;

            case PROFESSOR:

                response.sendRedirect("/professor/home");
                break;

            case REPRESENTANTE:

                response.sendRedirect(
                        "/turma/" +
                        usuario.getTurma().getCodigo());
                break;

            case ALUNO:

                response.sendRedirect(
                        "/turma/" +
                        usuario.getTurma().getCodigo());
                break;

            default:

                response.sendRedirect("/");
                break;
        }
    }

}