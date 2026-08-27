package br.edu.ifsuldeminas.colabclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.ProfessorTurma;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.repository.ProfessorTurmaRepository;

@Service
public class ProfessorTurmaService {

    private final ProfessorTurmaRepository repository;

    public ProfessorTurmaService(
            ProfessorTurmaRepository repository) {

        this.repository = repository;
    }


    public List<ProfessorTurma> listarPorProfessor(
            Usuario professor){

        return repository.findByProfessor(professor);
    }

    public void salvar(ProfessorTurma professorTurma){

        repository.save(professorTurma);
    }

    public void excluirTodos(Usuario professor){

        repository.deleteByProfessor(professor);
    }

    public boolean professorPossuiTurma(
        Usuario professor,
        Turma turma){

    return repository.existsByProfessorAndTurma(
            professor,
            turma);

    }

    public List<ProfessorTurma> listarHomeProfessor(
            Usuario professor){

        return repository
                .findByProfessorAndMostrarNaHomeTrue(
                        professor);

    }

    public void atualizarMurais(
        Usuario professor,
        List<Turma> todasTurmas,
        List<Long> turmasSelecionadas) {

    for (Turma turma : todasTurmas) {

        boolean selecionada =
                turmasSelecionadas != null &&
                turmasSelecionadas.contains(turma.getId());

        ProfessorTurma vinculo =
                repository.findByProfessorAndTurma(
                        professor,
                        turma);

        if (selecionada) {

            if (vinculo == null) {

                vinculo = new ProfessorTurma();
                vinculo.setProfessor(professor);
                vinculo.setTurma(turma);

            }

            vinculo.setMostrarNaHome(true);

            repository.save(vinculo);

        } else {

            if (vinculo != null) {

                repository.delete(vinculo);

            }

        }

    }

}
    

}