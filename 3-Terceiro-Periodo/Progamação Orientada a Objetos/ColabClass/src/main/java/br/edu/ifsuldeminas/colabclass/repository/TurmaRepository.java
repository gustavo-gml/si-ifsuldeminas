package br.edu.ifsuldeminas.colabclass.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.Turma;

public interface TurmaRepository
        extends JpaRepository<Turma, Long> {

    Optional<Turma> findByCodigo(String codigo);

}