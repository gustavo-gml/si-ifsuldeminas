package br.edu.ifsuldeminas.colabclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.Aviso;
import br.edu.ifsuldeminas.colabclass.model.Turma;

public interface AvisoRepository
        extends JpaRepository<Aviso, Long> {

    List<Aviso> findByTurma(Turma turma);
    
}

