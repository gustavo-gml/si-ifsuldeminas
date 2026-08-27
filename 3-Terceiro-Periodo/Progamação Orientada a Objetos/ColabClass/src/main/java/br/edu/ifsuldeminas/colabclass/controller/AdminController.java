package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifsuldeminas.colabclass.service.AvisoService;
import br.edu.ifsuldeminas.colabclass.service.DisciplinaService;
import br.edu.ifsuldeminas.colabclass.service.EventoService;
import br.edu.ifsuldeminas.colabclass.service.LinkRapidoService;
import br.edu.ifsuldeminas.colabclass.service.TurmaService;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;

@Controller
public class AdminController {

    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final EventoService eventoService;
    private final AvisoService avisoService;
    private final DisciplinaService disciplinaService;
    private final LinkRapidoService linkRapidoService;

    public AdminController(
            UsuarioService usuarioService,
            TurmaService turmaService,
            EventoService eventoService,
            AvisoService avisoService,
            DisciplinaService disciplinaService,
            LinkRapidoService linkRapidoService) {

        this.usuarioService = usuarioService;
        this.turmaService = turmaService;
        this.eventoService = eventoService;
        this.avisoService = avisoService;
        this.disciplinaService = disciplinaService;
        this.linkRapidoService = linkRapidoService;
    }

    @GetMapping("/admin")
    public String painelAdmin(Model model) {

        model.addAttribute("usuarios",
                usuarioService.quantidadeUsuarios());

        model.addAttribute("turmas",
                turmaService.quantidadeTurmas());

        model.addAttribute("eventos",
                eventoService.quantidadeEventos());

        model.addAttribute("avisos",
                avisoService.quantidadeAvisos());

        model.addAttribute("disciplinas",
                disciplinaService.quantidadeDisciplinas());

        model.addAttribute("links",
                linkRapidoService.quantidadeLinks());

        model.addAttribute("listaTurmas",
                turmaService.listarTodas());

        return "painel-admin";
    }
}