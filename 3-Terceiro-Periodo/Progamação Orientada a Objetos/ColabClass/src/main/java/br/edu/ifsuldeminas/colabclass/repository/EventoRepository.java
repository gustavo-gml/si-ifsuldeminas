package br.edu.ifsuldeminas.colabclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.Evento;
import br.edu.ifsuldeminas.colabclass.model.Turma;

public interface EventoRepository
        extends JpaRepository<Evento, Long> {

    List<Evento> findByTurmaOrderByDataAsc(
            Turma turma);
}