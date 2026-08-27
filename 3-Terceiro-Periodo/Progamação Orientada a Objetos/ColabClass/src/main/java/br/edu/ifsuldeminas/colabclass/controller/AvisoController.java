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

import br.edu.ifsuldeminas.colabclass.model.Aviso;
import br.edu.ifsuldeminas.colabclass.model.Role;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.service.AvisoService;
import br.edu.ifsuldeminas.colabclass.service.TurmaService;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;

@Controller
@RequestMapping("/aviso")
public class AvisoController {

    private final AvisoService avisoService;
    private final TurmaService turmaService;
    private final UsuarioService usuarioService;

   public AvisoController(
        AvisoService avisoService,
        TurmaService turmaService,
        UsuarioService usuarioService) {

       this.avisoService = avisoService;
       this.turmaService = turmaService;
       this.usuarioService = usuarioService;
   }

    @GetMapping("/novo")
    public String novoAviso(Model model){

        model.addAttribute(
                "aviso",
                new Aviso()
        );

        model.addAttribute(
                "turmas",
                turmaService.listarTodas()
        );

        return "novo-aviso";
    }

       @PostMapping("/salvar")
        public String salvar(
                @ModelAttribute Aviso aviso,
                Authentication authentication) {

        if (aviso.getId() != null) {

                Aviso avisoBanco =
                        avisoService.buscarPorId(aviso.getId());

                avisoBanco.setTitulo(aviso.getTitulo());
                avisoBanco.setDescricao(aviso.getDescricao());
                avisoBanco.setTurma(aviso.getTurma());

                avisoBanco.setUltimaEdicao(LocalDateTime.now());

                avisoService.salvar(avisoBanco);

                return "redirect:/turma/" +
                        avisoBanco.getTurma().getCodigo() +
                        "?editado";
        }

        Usuario usuario =
                usuarioService.buscarPorEmail(authentication.getName());

        aviso.setAutor(usuario);
        aviso.setDataCriacao(LocalDateTime.now());

        avisoService.salvar(aviso);

        return "redirect:/turma/" +
                aviso.getTurma().getCodigo() +
                "?sucesso";
        }

    @GetMapping("/editar/{id}")
        public String editar(
                @PathVariable Long id,
                Authentication authentication,
                Model model){

        Aviso aviso =
                avisoService.buscarPorId(id);

        model.addAttribute(
                "aviso",
                aviso);

        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        if (usuario.getRole() != Role.ADMIN &&
        !aviso.getAutor().getId().equals(usuario.getId())) {

        return "redirect:/403";
        }

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        return "novo-aviso";

    }
        @GetMapping("/excluir/{id}")
        public String excluir(
        @PathVariable Long id,
        Authentication authentication){

        Aviso aviso =
                avisoService.buscarPorId(id);

        Usuario usuario =
                usuarioService.buscarPorEmail(
                        authentication.getName());

        if (usuario.getRole() != Role.ADMIN &&
        !aviso.getAutor().getId().equals(usuario.getId())) {

        return "redirect:/403";
        }

        String codigoTurma =
                aviso.getTurma().getCodigo();

        avisoService.excluir(id);

        return "redirect:/turma/" +
                codigoTurma +
                "?excluido";

    }

    @GetMapping("/listar")
    public String listar(Model model){

        model.addAttribute(
                "avisos",
                avisoService.listarTodos());

        return "listar-avisos";
    }


    
}