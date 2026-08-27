package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.colabclass.model.LinkRapido;
import br.edu.ifsuldeminas.colabclass.service.LinkRapidoService;
import br.edu.ifsuldeminas.colabclass.service.TurmaService;

@Controller
@RequestMapping("/link")
public class LinkRapidoController {

    private final LinkRapidoService linkRapidoService;
    private final TurmaService turmaService;

    public LinkRapidoController(
            LinkRapidoService linkRapidoService,
            TurmaService turmaService) {

        this.linkRapidoService = linkRapidoService;
        this.turmaService = turmaService;
    }

    @GetMapping("/novo")
    public String novo(Model model){

        model.addAttribute(
                "linkRapido",
                new LinkRapido()
        );

        model.addAttribute(
                "turmas",
                turmaService.listarTodas()
        );

        return "novo-link";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute LinkRapido linkRapido){

        boolean editando =
                (linkRapido.getId() != null);

        linkRapidoService.salvar(linkRapido);

        if(editando){

            return "redirect:/turma/" +
                    linkRapido.getTurma().getCodigo()
                    + "?editado";
        }

        return "redirect:/turma/" +
                linkRapido.getTurma().getCodigo()
                + "?sucesso";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model){

        LinkRapido linkRapido =
                linkRapidoService.buscarPorId(id);

        model.addAttribute(
                "linkRapido",
                linkRapido);

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        return "novo-link";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id){

        LinkRapido linkRapido =
                linkRapidoService.buscarPorId(id);

        String codigoTurma =
                linkRapido.getTurma().getCodigo();

        linkRapidoService.excluir(id);

        return "redirect:/turma/" +
                codigoTurma +
                "?excluido";
    }

        @GetMapping("/listar")
        public String listar(Model model){

        model.addAttribute(
                "links",
                linkRapidoService.listarTodos());

        return "listar-links";
        }
}