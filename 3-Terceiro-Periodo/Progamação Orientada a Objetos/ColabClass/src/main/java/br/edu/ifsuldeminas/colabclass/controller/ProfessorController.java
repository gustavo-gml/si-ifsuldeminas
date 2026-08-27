package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.service.ProfessorTurmaService;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorTurmaService professorTurmaService;
    private final UsuarioService usuarioService;

    public ProfessorController(
            ProfessorTurmaService professorTurmaService,
            UsuarioService usuarioService) {

        this.professorTurmaService = professorTurmaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/home")
    public String home(
            Authentication authentication,
            Model model) {

        Usuario professor =
                usuarioService.buscarPorEmail(
                        authentication.getName());

    model.addAttribute(
            "turmas",
            professorTurmaService.listarHomeProfessor(professor));

        return "professor-home";
    }

}