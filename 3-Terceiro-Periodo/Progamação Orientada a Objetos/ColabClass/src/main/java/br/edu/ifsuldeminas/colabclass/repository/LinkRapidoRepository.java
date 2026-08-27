package br.edu.ifsuldeminas.colabclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.LinkRapido;
import br.edu.ifsuldeminas.colabclass.model.Turma;

public interface LinkRapidoRepository
        extends JpaRepository<LinkRapido, Long> {

    List<LinkRapido> findByTurma(Turma turma);
}