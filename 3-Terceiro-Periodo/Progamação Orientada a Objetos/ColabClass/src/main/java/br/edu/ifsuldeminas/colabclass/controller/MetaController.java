package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.colabclass.model.Meta;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.service.MetaService;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;

@Controller
@RequestMapping("/meta")
public class MetaController {

    private final MetaService metaService;
    private final UsuarioService usuarioService;

    public MetaController(
            MetaService metaService,
            UsuarioService usuarioService) {

        this.metaService = metaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/listar")
    public String listar(
            Authentication authentication,
            Model model) {

        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        model.addAttribute(
                "metas",
                metaService.listarPorUsuario(usuario));

        return "listar-metas";
    }

    @GetMapping("/nova")
    public String nova(Model model) {

        model.addAttribute(
                "meta",
                new Meta());

        return "nova-meta";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Meta meta,
            Authentication authentication) {

        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        meta.setUsuario(usuario);

        metaService.salvar(meta);

        return "redirect:/meta/listar";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "meta",
                metaService.buscarPorId(id));

        return "nova-meta";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id) {

        metaService.excluir(id);

        return "redirect:/meta/listar";
    }

    @GetMapping("/concluir/{id}")
    public String concluir(
            @PathVariable Long id) {

        Meta meta =
                metaService.buscarPorId(id);

        meta.setConcluida(true);

        metaService.salvar(meta);

        return "redirect:/meta/listar";
    }

    

    

}