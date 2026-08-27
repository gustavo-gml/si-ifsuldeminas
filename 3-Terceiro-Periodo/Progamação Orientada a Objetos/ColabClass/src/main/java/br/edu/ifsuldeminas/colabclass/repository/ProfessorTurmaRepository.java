package br.edu.ifsuldeminas.colabclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.ProfessorTurma;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.model.Usuario;

public interface ProfessorTurmaRepository
        extends JpaRepository<ProfessorTurma, Long>{

    List<ProfessorTurma> findByProfessor(Usuario professor);

    List<ProfessorTurma> findByProfessorAndMostrarNaHomeTrue(
            Usuario professor);

    void deleteByProfessor(Usuario professor);

    boolean existsByProfessorAndTurma(
            Usuario professor,
            Turma turma);

    ProfessorTurma findByProfessorAndTurma(
            Usuario professor,
            Turma turma);

    void deleteByProfessorAndTurma(
            Usuario professor,
            Turma turma);

}