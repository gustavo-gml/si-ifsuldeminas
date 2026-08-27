package br.edu.ifsuldeminas.colabclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.Disciplina;
import br.edu.ifsuldeminas.colabclass.model.Turma;

public interface DisciplinaRepository
        extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByTurma(Turma turma);
    List<Disciplina> findByTurmaAndAtivaTrue(Turma turma);
    long countByTurma(Turma turma);

}