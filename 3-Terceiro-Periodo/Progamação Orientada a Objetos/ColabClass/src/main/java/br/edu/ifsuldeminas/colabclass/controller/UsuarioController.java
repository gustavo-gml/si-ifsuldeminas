package br.edu.ifsuldeminas.colabclass.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.ifsuldeminas.colabclass.model.ProfessorTurma;
import br.edu.ifsuldeminas.colabclass.model.Role;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.service.ProfessorTurmaService;
import br.edu.ifsuldeminas.colabclass.service.TurmaService;
import br.edu.ifsuldeminas.colabclass.service.UsuarioService;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final PasswordEncoder passwordEncoder;
    private final ProfessorTurmaService professorTurmaService;
    

    public UsuarioController(
            UsuarioService usuarioService,
            TurmaService turmaService,
            PasswordEncoder passwordEncoder,
            ProfessorTurmaService professorTurmaService) {

        this.usuarioService = usuarioService;
        this.turmaService = turmaService;
        this.passwordEncoder = passwordEncoder;
        this.professorTurmaService = professorTurmaService;
    }

    @GetMapping("/novo")
    public String novoUsuario(Model model) {

        model.addAttribute(
                "usuario",
                new Usuario());

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        return "novo-usuario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Usuario usuario) {

        usuario.setRole(Role.ALUNO);

        usuario.setSenha(
        passwordEncoder.encode(
                usuario.getSenha()));

        usuarioService.salvar(usuario);

        return "redirect:/";
    }

    @GetMapping("/listar")
    public String listar(Model model) {

        model.addAttribute(
                "usuarios",
                usuarioService.listarTodos());

        return "listar-usuarios";
    }

    @GetMapping("/representante/{id}")
    public String representante(
            @PathVariable Long id){

        usuarioService
                .tornarRepresentante(id);

        return "redirect:/usuario/listar";
    }

    @GetMapping("/admin/{id}")
    public String admin(
            @PathVariable Long id){

        usuarioService
                .tornarAdmin(id);

        return "redirect:/usuario/listar";
    }

    @GetMapping("/aluno/{id}")
    public String aluno(
            @PathVariable Long id){

        usuarioService
                .tornarAluno(id);

        return "redirect:/usuario/listar";
    }

    @GetMapping("/perfil/{id}")
    public String perfil(
        @PathVariable Long id,
        Model model){

    Usuario usuario =
            usuarioService.buscarPorId(id);

    model.addAttribute(
            "usuario",
            usuario);

    model.addAttribute(
            "roles",
            Role.values());

    return "perfil-usuario";
    }

    @GetMapping("/perfil")
    public String perfil(
        Authentication authentication,
        Model model) {

    Usuario usuario =
            usuarioService.buscarPorEmail(
                    authentication.getName());

    model.addAttribute(
            "usuario",
            usuario);

    return "perfil-usuario";
    }

    @GetMapping("/editar")
    public String editarPerfil(
        Authentication authentication,
        Model model) {

    Usuario usuario =
            usuarioService.buscarPorEmail(
                    authentication.getName());

    model.addAttribute(
            "usuario",
            usuario);

    return "editar-usuario";
    }

    @PostMapping("/atualizar")
    public String atualizar(
        @ModelAttribute Usuario usuario) {

    Usuario usuarioBanco =
            usuarioService.buscarPorId(
                    usuario.getId());

    usuarioBanco.setNome(
            usuario.getNome());

    if (!usuario.getSenha().isBlank()) {

        usuarioBanco.setSenha(

                passwordEncoder.encode(
                        usuario.getSenha())

        );

    }

    

    usuarioService.salvar(
            usuarioBanco);

    return "redirect:/usuario/perfil";
    }

    @GetMapping("/professor/{id}")
        public String professor(
                @PathVariable Long id){

        usuarioService
                .tornarProfessor(id);

        return "redirect:/usuario/listar";
        }

        @GetMapping("/turmas/{id}")
        public String gerenciarTurmas(
                @PathVariable Long id,
                Model model){

        Usuario usuario =
                usuarioService.buscarPorId(id);

        model.addAttribute("usuario", usuario);

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        model.addAttribute(
                "professorTurmas",
                professorTurmaService
                        .listarPorProfessor(usuario));

        return "usuario-turmas";
        }

    @PostMapping("/turmas/salvar")
        public String salvarTurmas(
        @RequestParam Long id,
        @RequestParam(required = false)
        List<Long> turmasProfessor){

    Usuario professor =
            usuarioService.buscarPorId(id);

    professorTurmaService.excluirTodos(professor);

    if(turmasProfessor != null){

        for(Long turmaId : turmasProfessor){

            ProfessorTurma pt =
                    new ProfessorTurma();

            pt.setProfessor(professor);

            pt.setTurma(
                turmaService.buscarPorId(turmaId)
            );

            professorTurmaService.salvar(pt);

        }

    }

    return "redirect:/usuario/listar";
        }


        @GetMapping("/{id}/murais")
        public String gerenciarMurais(
                @PathVariable Long id,
                Model model) {

        Usuario professor = usuarioService.buscarPorId(id);

        if (professor == null) {
                return "redirect:/usuario/listar";
        }

        model.addAttribute("professor", professor);

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        List<Long> selecionadas =
                professorTurmaService
                        .listarPorProfessor(professor)
                        .stream()
                        .map(pt -> pt.getTurma().getId())
                        .toList();

        model.addAttribute(
                "selecionadas",
                selecionadas);

        return "usuario-murais";
        }

@PostMapping("/{id}/murais")
public String salvarMurais(

        @PathVariable Long id,

        @RequestParam(required = false)
        List<Long> turmasSelecionadas) {

    Usuario professor =
            usuarioService.buscarPorId(id);

    if (professor == null) {
        return "redirect:/usuario/listar";
    }

    professorTurmaService.atualizarMurais(

            professor,

            turmaService.listarTodas(),

            turmasSelecionadas);

    return "redirect:/usuario/" + id + "/murais";
}



        

        


}