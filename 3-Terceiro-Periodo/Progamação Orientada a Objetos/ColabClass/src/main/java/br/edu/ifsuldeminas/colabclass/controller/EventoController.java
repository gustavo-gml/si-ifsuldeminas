package br.edu.ifsuldeminas.colabclass.controller;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.colabclass.model.Evento;
import br.edu.ifsuldeminas.colabclass.model.Role;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.model.enums.StatusEvento;
import br.edu.ifsuldeminas.colabclass.service.EntregaEventoService;
import br.edu.ifsuldeminas.colabclass.service.EventoService;
import br.edu.ifsuldeminas.colabclass.service.TurmaService;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;
import br.edu.ifsuldeminas.colabclass.service.EntregaEventoService;

@Controller
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;
    private final TurmaService turmaService;
    private final UsuarioService usuarioService;
    private final EntregaEventoService entregaEventoService;

        public EventoController(
                EventoService eventoService,
                TurmaService turmaService,
                UsuarioService usuarioService,EntregaEventoService entregaEventoService) {

        this.eventoService = eventoService;
        this.turmaService = turmaService;
        this.usuarioService = usuarioService;
        this.entregaEventoService =
        entregaEventoService;
        }

    @GetMapping("/novo")
    public String novoEvento(Model model){

        model.addAttribute(
                "evento",
                new Evento()
        );

        model.addAttribute(
                "turmas",
                turmaService.listarTodas()
        );

        return "novo-evento";
    }

        @PostMapping("/salvar")
        public String salvar(
                @ModelAttribute Evento evento,
                Authentication authentication) {

        if (evento.getId() != null) {

                // Edição
                Evento eventoBanco =
                        eventoService.buscarPorId(evento.getId());

                eventoBanco.setTitulo(evento.getTitulo());
                eventoBanco.setData(evento.getData());
                eventoBanco.setTipo(evento.getTipo());
                eventoBanco.setObservacoes(evento.getObservacoes());
                eventoBanco.setTurma(evento.getTurma());
                eventoBanco.setDisciplina(evento.getDisciplina());

                eventoBanco.setUltimaEdicao(LocalDateTime.now());

                eventoService.salvar(eventoBanco);

                return "redirect:/turma/" +
                        eventoBanco.getTurma().getCodigo() +
                        "?editado";
        }

        // Novo evento
        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        evento.setAutor(usuario);
        evento.setDataPublicacao(LocalDateTime.now());

        eventoService.criarEvento(evento);

        return "redirect:/turma/" +
                evento.getTurma().getCodigo() +
                "?sucesso";
        }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Authentication authentication,
            Model model) {

        Evento evento =
                eventoService.buscarPorId(id);
                Usuario usuario =
        usuarioService.buscarPorEmail(
                authentication.getName());

        if (usuario.getRole() != Role.ADMIN &&
        !evento.getAutor().getId().equals(usuario.getId())) {

        return "redirect:/403";
        }

        model.addAttribute(
                "evento",
                evento);

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        return "novo-evento";
    }

                @GetMapping("/excluir/{id}")
        public String excluir(
                @PathVariable Long id,
                Authentication authentication) {

        Evento evento =
                eventoService.buscarPorId(id);

        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        if (usuario.getRole() != Role.ADMIN &&
                !evento.getAutor().getId().equals(usuario.getId())) {

                return "redirect:/403";
        }

        String codigoTurma =
                evento.getTurma().getCodigo();

        eventoService.excluir(id);

        return "redirect:/turma/" +
                codigoTurma +
                "?excluido";
        }

        @GetMapping("/listar")
        public String listar(Model model) {

        model.addAttribute(
                "eventos",
                eventoService.listarTodos());

        return "listar-eventos";
        }

        
@GetMapping("/entregar/{id}")
public String entregar(
        @PathVariable Long id,
        Authentication authentication) {

    if (authentication == null) {
        return "redirect:/login";
    }

    Evento evento = eventoService.buscarPorId(id);

    if (evento == null) {
        return "redirect:/turma";
    }

    Usuario usuario =
            usuarioService.buscarPorEmail(authentication.getName());

    if (usuario == null) {
        return "redirect:/login";
    }

    entregaEventoService.marcarComoEntregue(evento, usuario);

    return "redirect:/turma/" + evento.getTurma().getCodigo();
}
        

    
}