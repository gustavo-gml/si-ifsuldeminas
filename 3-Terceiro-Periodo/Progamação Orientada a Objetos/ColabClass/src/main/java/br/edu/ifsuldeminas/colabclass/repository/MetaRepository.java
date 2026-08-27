package br.edu.ifsuldeminas.colabclass.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsuldeminas.colabclass.model.Meta;
import br.edu.ifsuldeminas.colabclass.model.Usuario;

public interface MetaRepository
        extends JpaRepository<Meta, Long>{

    List<Meta> findByUsuario(Usuario usuario);

}