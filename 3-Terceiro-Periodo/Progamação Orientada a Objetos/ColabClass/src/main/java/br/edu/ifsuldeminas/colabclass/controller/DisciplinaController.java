package br.edu.ifsuldeminas.colabclass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifsuldeminas.colabclass.model.Disciplina;
import br.edu.ifsuldeminas.colabclass.service.DisciplinaService;
import br.edu.ifsuldeminas.colabclass.service.TurmaService;

@Controller
@RequestMapping("/disciplina")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;
    private final TurmaService turmaService;

    public DisciplinaController(
            DisciplinaService disciplinaService,
            TurmaService turmaService) {

        this.disciplinaService = disciplinaService;
        this.turmaService = turmaService;
    }

    @GetMapping("/listar")
    public String listar(Model model){

        model.addAttribute(
                "disciplinas",
                disciplinaService.listarTodas()
        );

        return "listar-disciplinas";
    }

    

    @GetMapping("/nova")
    public String novaDisciplina(Model model){

        model.addAttribute(
                "disciplina",
                new Disciplina());

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        return "nova-disciplina";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Disciplina disciplina){

        boolean editando =
                (disciplina.getId() != null);

        disciplinaService.salvar(
                disciplina);

        if(editando){

            return "redirect:/turma/" +
                    disciplina.getTurma().getCodigo()
                    + "?editado";
        }

        return "redirect:/turma/" +
                disciplina.getTurma().getCodigo()
                + "?sucesso";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model){

        Disciplina disciplina =
                disciplinaService.buscarPorId(id);

        model.addAttribute(
                "disciplina",
                disciplina);

        model.addAttribute(
                "turmas",
                turmaService.listarTodas());

        return "nova-disciplina";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id){

        Disciplina disciplina =
                disciplinaService.buscarPorId(id);

        String codigoTurma =
                disciplina
                        .getTurma()
                        .getCodigo();

        disciplinaService.excluir(id);

        return "redirect:/turma/" +
                codigoTurma +
                "?excluido";
    }

    
}