package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifsuldeminas.colabclass.model.Role;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;

@Controller
public class HomeController {

    private final UsuarioService usuarioService;

    public HomeController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/home")
    public String home(Authentication authentication) {

        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        if (usuario.getRole() == Role.ADMIN) {

            return "redirect:/admin";

        }

        if (usuario.getRole() == Role.PROFESSOR) {

            return "redirect:/professor/home";

        }

        if (usuario.getTurma() == null) {

            return "redirect:/usuario/perfil";

        }

        return "redirect:/turma/" +
                usuario.getTurma().getCodigo();

    }
}
