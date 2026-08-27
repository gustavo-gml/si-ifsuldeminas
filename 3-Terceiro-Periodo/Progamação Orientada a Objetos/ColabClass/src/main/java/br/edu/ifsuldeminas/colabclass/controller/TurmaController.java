package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.service.AvisoService;
import br.edu.ifsuldeminas.colabclass.service.DisciplinaService;
import br.edu.ifsuldeminas.colabclass.service.EventoService;
import br.edu.ifsuldeminas.colabclass.service.LinkRapidoService;
import br.edu.ifsuldeminas.colabclass.service.TurmaService;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;
import br.edu.ifsuldeminas.colabclass.service.EntregaEventoService;

import java.util.List;

@Controller
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;
    private final DisciplinaService disciplinaService;
    private final AvisoService avisoService;
    private final EventoService eventoService;
    private final LinkRapidoService linkRapidoService;
    private final UsuarioService usuarioService;
    private final EntregaEventoService entregaEventoService;

    public TurmaController(
            TurmaService turmaService,
            DisciplinaService disciplinaService,
            AvisoService avisoService,
            EventoService eventoService,
            LinkRapidoService linkRapidoService,
            UsuarioService usuarioService,
            EntregaEventoService entregaEventoService) {

        this.turmaService = turmaService;
        this.disciplinaService = disciplinaService;
        this.avisoService = avisoService;
        this.eventoService = eventoService;
        this.linkRapidoService = linkRapidoService;
        this.usuarioService = usuarioService;
        this.entregaEventoService = entregaEventoService;
    }

    @GetMapping("/nova")
    public String novaTurma(Model model) {
        model.addAttribute("turma", new Turma());
        return "nova-turma";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Turma turma) {

        boolean editando = (turma.getId() != null);

        turmaService.salvar(turma);

        if (editando) {
            return "redirect:/admin?editado";
        }

        return "redirect:/admin?sucesso";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("turmas", turmaService.listarTodas());
        return "listar-turmas";
    }

    @GetMapping
    public String acessarTurma(@RequestParam String codigo) {
        return "redirect:/turma/" + codigo;
    }

    @GetMapping("/{codigo}")
    public String muralTurma(
            @PathVariable String codigo,
            Model model,
            Authentication authentication) {

        Turma turma = turmaService.buscarPorCodigo(codigo);

        if (turma == null) {
            return "redirect:/";
        }

        Usuario usuario =
                usuarioService.buscarPorEmail(authentication.getName());

        List<br.edu.ifsuldeminas.colabclass.model.Evento> eventos =
                eventoService.listarPorTurma(turma);

        model.addAttribute("turma", turma);
        model.addAttribute("disciplinas", disciplinaService.listarPorTurma(turma));
        model.addAttribute("avisos", avisoService.listarPorTurma(turma));
        model.addAttribute("eventos", eventos);
        model.addAttribute("linksRapidos", linkRapidoService.listarPorTurma(turma));

        // 🔥 AQUI ESTÁ O FIX REAL (STATUS POR USUÁRIO)
        model.addAttribute(
        "entregas",
        entregaEventoService.mapPorUsuarioETurma(usuario, turma, eventos)
);

        return "mural-turma";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Turma turma = turmaService.buscarPorId(id);

        model.addAttribute("turma", turma);

        return "nova-turma";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {

        boolean excluiu = turmaService.excluir(id);

        if (!excluiu) {
            return "redirect:/admin?erroExclusao";
        }

        return "redirect:/admin?excluido";
    }
}