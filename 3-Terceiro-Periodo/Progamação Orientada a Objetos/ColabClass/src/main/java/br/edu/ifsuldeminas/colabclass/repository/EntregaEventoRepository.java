package br.edu.ifsuldeminas.colabclass.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.EntregaEvento;
import br.edu.ifsuldeminas.colabclass.model.Evento;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.model.Usuario;

public interface EntregaEventoRepository extends JpaRepository<EntregaEvento, Long> {

    Optional<EntregaEvento> findByUsuarioAndEvento(
            Usuario usuario,
            Evento evento);

Optional<EntregaEvento> findByEventoAndUsuario(Evento evento, Usuario usuario);

    List<EntregaEvento> findByUsuario(Usuario usuario);

    List<EntregaEvento> findByEvento(Evento evento);

    List<EntregaEvento> findByEvento_Turma(Turma turma);

    List<EntregaEvento> findByUsuarioAndEvento_Turma(Usuario usuario, Turma turma);

    
}